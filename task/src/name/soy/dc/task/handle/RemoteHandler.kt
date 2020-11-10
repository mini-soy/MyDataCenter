package name.soy.dc.task.handle

import name.soy.dc.task.exe.RemoteExecutable

class RemoteHandler {

	companion object{
		private lateinit var handle:(RemoteExecutable)->RemoteExecutable.RemoteProgress
		
		fun addRemoteHandle(handler:(RemoteExecutable)->RemoteExecutable.RemoteProgress) {
			handle = handler
		}
		operator fun invoke(exe:RemoteExecutable) = handle(exe)
	}
}