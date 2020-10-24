package name.soy.dc.tasks.exe

import name.soy.dc.DataCenter
import name.soy.dc.client.Client
import name.soy.dc.protocol.packets.task.TaskCreate
import name.soy.dc.protocol.packets.task.TaskResult
import name.soy.dc.protocol.packets.task.TaskState
import name.soy.dc.task.ProgressStat
import name.soy.dc.task.exe.RemoteExecutable

class RemoteHandle(exe: RemoteExecutable) : RemoteExecutable.RemoteProgress(exe) {

	private val lock = Object()
	private lateinit var resultPacket: TaskResult
	override fun run(): Int {
		var local = true
		var client: Client? = null
		if (dataset.containsKey(RemoteExecutable.TARGET_DEVICE)) {
			var remoteAddr = dataset[RemoteExecutable.TARGET_DEVICE] as String
			client = DataCenter().client()[remoteAddr]
			local = client.isLocal()
		}
		if (!local) {
			var localexe = exe.localExecute()
			localexe.setData(dataset)
			localexe.addProgressStatChangeListener {
				progress = it.new.progress
				stat = it.new.stat
				progressText = it.new.text
			}
			localexe.addResultListener { it.resCode = resCode }
			return localexe.run()
		} else {
			val cr = TaskCreate(dataset, uid, exe.getName())
			client!!.sendPacket(cr)
			synchronized(this) {
				lock.wait()
			}
			resCode = resultPacket.code
			return resCode
		}
	}

	operator fun plus(stat: TaskResult) {
		synchronized(this) {
			lock.notify()
		}
		resultPacket = stat;
	}

	operator fun plus(stat: TaskState) {
		stat.also {
			progress = it.progress
			this.stat = ProgressStat.values()[it.stat]
			progressText = it.text
		}
	}

}