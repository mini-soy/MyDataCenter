package name.soy.dc.task

import name.soy.dc.task.exe.RemoteExecutable

class RemoteHandler {

	companion object{
		private lateinit var handle:(RemoteExecutable)->RemoteExecutable.RemoteProgress
		fun addRemoteHandle(handler:(RemoteExecutable)->RemoteExecutable.RemoteProgress){
			handle = handler
		}
		operator fun invoke(exe:RemoteExecutable):RemoteExecutable.RemoteProgress = handle(exe)
	}
}