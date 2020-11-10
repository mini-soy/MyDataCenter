package name.soy.dc.tasks.exe

import name.soy.dc.DataCenter
import name.soy.dc.client.Client
import name.soy.dc.protocol.packets.task.TaskCreate
import name.soy.dc.protocol.packets.task.TaskResult
import name.soy.dc.protocol.packets.task.TaskState
import name.soy.dc.task.ProgressStat
import name.soy.dc.task.exe.RemoteExecutable

class CloudRemoteHandle(exe: RemoteExecutable) : RemoteExecutable.RemoteProgress(exe) {
	companion object {
		/**
		 * 仅服务器
		 */
		const val TARGET_DEVICE = "target_device"
	}
	private val lock = Object()
	
	private lateinit var resultPacket: TaskResult
	
	override fun run(): Int {
		var local = true
		var client: Client? = null
		if (dataset.containsKey(TARGET_DEVICE)) {
			val remoteAddr = dataset[TARGET_DEVICE] as String
			client = DataCenter().client()[remoteAddr]
			local = client.isLocal()
		}
		if (!local) {
			val localExe = exe.localExecute()
			localExe.setData(dataset)
			localExe.addProgressStatChangeListener {
				progress = it.new.progress
				stat = it.new.stat
				progressText = it.new.text
			}
			localExe.addResultListener { resCode = it.resCode }
			return localExe.run()
		} else {
			val cr = TaskCreate(dataset, uid, exe.getName())
			client?.sendPacket(cr)
			synchronized(lock, lock::wait)
			resCode = resultPacket.code
			return resCode
		}
	}

	operator fun plus(stat: TaskResult) {
		synchronized(lock, lock::notify)
		resultPacket = stat
	}

	operator fun plus(stat: TaskState) {
		stat.also {
			progress = it.progress
			this.stat = ProgressStat.values()[it.stat]
			progressText = it.text
		}
	}

}