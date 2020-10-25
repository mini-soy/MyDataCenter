package name.soy.dc.task.exe.minecraft

import name.soy.dc.task.Aligns
import name.soy.dc.task.exe.Executable
import name.soy.dc.task.exe.RemoteExecutable

class FabricCompile: RemoteExecutable() {
	override fun remoteParameters(): () -> HashMap<String, Aligns<*>> ={
		HashMap<String,Aligns<*>>().apply{
			
		}
	}

	override fun localExecute(): Executable.ExecuteProgress {
		TODO("Not yet implemented")
	}

	override fun getName(): String = "fabric compile"

	override fun returnParameters(): () -> HashMap<String, Class<*>> {
		TODO("Not yet implemented")
	}
}