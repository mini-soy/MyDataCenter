package name.soy.dc.tasks.exe

import lombok.AllArgsConstructor
import name.soy.dc.tasks.exe.FileDownload

@AllArgsConstructor
enum class Executables {
	COMMAND(Command::class.java), FILE_DOWNLOAD(FileDownload::class.java);

	var exe_class: Class<out Executable>? = null
}