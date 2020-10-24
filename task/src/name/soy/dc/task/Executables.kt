package name.soy.dc.task

import name.soy.dc.task.exe.*
import name.soy.dc.task.exe.minecraft.SpigotCompile


enum class Executables(val exe: Executable) {
	COMMAND(Command()),
	FILE_DOWNLOAD(FileDownload()),
	JENKINS_DOWNLOAD(JenkinsDownload()),
	SPIGOT_COMPILE(SpigotCompile());
	init {
		Executables.exemap[exe.getName()] = exe
	}
	companion object{
		val exemap = HashMap<String,Executable>()
	}
}