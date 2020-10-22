package name.soy.dc.tasks.exe


enum class Executables(val exe_class: Class<out Executable>) {
	COMMAND(Command::class.java),
	FILE_DOWNLOAD(FileDownload::class.java);
}