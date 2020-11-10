package name.soy.dc.client.task

import name.soy.dc.protocol.packets.task.TaskPost
import name.soy.dc.task.exe.RemoteExecutable

class ClientRemoteHandle(exe: RemoteExecutable) : RemoteExecutable.RemoteProgress(exe) {
	companion object {
	
	}
	/**
	 *
	 */
	override fun run(): Int {
		if (dataset[RemoteExecutable.TARGET_DEVICE]==null) {
			val post = TaskPost(this.uid,exe.getName());
			val localexe = exe.localExecute()
			localexe.setData(dataset)
			localexe.addProgressStatChangeListener {
				progress = it.new.progress
				stat = it.new.stat
				progressText = it.new.text
			}
			localexe.addResultListener { resCode = it.resCode }
			return localexe.run()
		} else {
			TODO("现在应该不需要吧...")
			
		}
	}
}