package name.soy.dc.task.exe

import name.soy.dc.task.Aligns
import name.soy.dc.task.RemoteHandler

/**
 * 多台设备间的操作，注意，本地操作也要实现！！！
 */
abstract class RemoteExecutable : Executable {
	companion object {
		const val TARGET_DEVICE = "target_device"
	}

	final override fun needParameters(): () -> HashMap<String, Aligns<*>> = {
		HashMap<String, Aligns<*>>().apply {
			this[TARGET_DEVICE] = Aligns.createString(null, null, false)
			this.putAll(remoteParameters().invoke())
		}
	}

	abstract fun remoteParameters(): () -> HashMap<String, Aligns<*>>

	final override fun execute(): RemoteProgress = RemoteHandler(this)

	abstract fun localExecute(): Executable.ExecuteProgress
	
	abstract class RemoteProgress(override var exe: RemoteExecutable) : Executable.ExecuteProgress(exe)
}