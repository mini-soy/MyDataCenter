package name.soy.dc.task

import name.soy.dc.task.exe.*
import name.soy.dc.task.exe.minecraft.SpigotCompile


class Executables(val exe: Executable) {

	
	operator fun invoke() = exe
	companion object{
		val COMMAND = this(Command())
		val FILE_DOWNLOAD = this(FileDownload())
		val JENKINS_DOWNLOAD = this(JenkinsDownload())
		val SPIGOT_COMPILE = this(SpigotCompile())
		
		
		
		val exemap = HashMap<String,Executable>();
		
		operator fun invoke(exe: Executable): Executables {
			exemap[exe.getName()] = exe
			return Executables(exe)
		}
	}
}