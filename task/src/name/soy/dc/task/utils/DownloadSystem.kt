package name.soy.dc.task.utils

import java.net.HttpURLConnection

object DownloadSystem {
	const val KB: Long = 1024L
	const val MB: Long = 1024L * 1024L
	const val GB: Long = 1024L * 1024L * 1024L
	const val TB: Long = 1024L * 1024L * 1024L * 1024L
	const val PB: Long = 1024L * 1024L * 1024L * 1024L * 1024L
	const val EB: Long = 1024L * 1024L * 1024L * 1024L * 1024L * 1024L
	fun download(conn: HttpURLConnection, threads: Int, speedLimit: Int): DownloadProgress {
		repeat(threads) {
		
		}
		return DownloadProgress();
	}
	
	fun sizeToText(size: Long): String = when (size) {
		in 0..KB -> "${String.format("%.2f", size)}Byte"
		in KB..MB -> "${String.format("%.2f", size.toDouble() / KB)}KB"
		in MB..GB -> "${String.format("%.2f", size.toDouble() / MB)}MB"
		in GB..TB -> "${String.format("%.2f", size.toDouble() / GB)}GB"
		in TB..PB -> "${String.format("%.2f", size.toDouble() / TB)}TB"
		in PB..EB -> "${String.format("%.2f", size.toDouble() / PB)}PB"
		else -> "${String.format("%.2f", size.toDouble() / EB)}EB"
	}
	
	data class DownloadProgress(@Volatile var size: Long = 0, @Volatile var text: String = "", @Volatile var progress: Int = 0, @Volatile var speed: Int = 0) {
		fun getSizeText() = sizeToText(size)
		
		
	}
}