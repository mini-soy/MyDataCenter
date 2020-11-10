package name.soy.dc.task.exe

import name.soy.dc.task.Aligns
import name.soy.dc.task.handle.FileCacheHandler
import name.soy.dc.task.utils.DownloadSystem
import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class FileDownload : RemoteExecutable() {
	companion object {
		const val URL = "url"
		const val UID = "download_id"
		const val RETRY_COUNT = "retry_count"
		const val FORCE_OVERWRITE = "force_overwrite"
		
		const val FILE_SIZE = "file_size"
		const val FILE_NAME = "file_name"
	}
	
	override fun remoteParameters(): () -> HashMap<String, Aligns<*>> = {
		HashMap<String, Aligns<*>>().apply {
			//目标地址,如:http://mapland.cn/download
			//下载概念UID,用于重复下载内容可以直接本地调用
			this[URL] = Aligns.createString(hashSetOf(), null, needed = true, list = false)
			this[UID] = Aligns.createString(hashSetOf(), null, needed = true, list = false)
			this[RETRY_COUNT] = Aligns.createInt(hashSetOf(), 3, needed = true, list = false)
			this[FORCE_OVERWRITE] = Aligns.createBoolean(false, list = false)
		}
	}
	
	override fun returnParameters(): () -> HashMap<String, Class<*>> = {
		HashMap<String, Class<*>>().apply {
			this[FILE_SIZE] = Int::class.java
			
			this[FILE_NAME] = String::class.java
		}
	}
	
	override fun localExecute() = LocalFileDownloadProgress(this)
	
	override fun getName() = "file download"
	
	class LocalFileDownloadProgress(exe: FileDownload) : Executable.ExecuteProgress(exe) {
		companion object {
		
		}
		
		/**
		 * 文件大小转换为
		 */
		
		
		fun downloadUID() = dataset[UID] as String
		
		override fun run(): Int {
			FileCacheHandler() + this
			
			if (FileCacheHandler().needDownload(downloadUID()) || dataset[FORCE_OVERWRITE] as Boolean) {
				FileCacheHandler()[downloadUID()]
				try {
					progressText = "开始下载,正在请求http(s)数据..."
					var url = dataset[URL] as String
					var conn = URL(url).openConnection() as HttpURLConnection
					conn.instanceFollowRedirects = true
					var filename = url.split("/").last()
					
					var dis: String? = conn.getHeaderField("Content-Disposition")
					if (dis != null && dis.contains("filename=")) {
						dis.split("filename=").last().split(";").first().apply {
							if (!isEmpty()) filename = this
						}
					}
					
					var size = conn.contentLengthLong
					progressText = "请求完毕!正在创建文件..."
					var file = FileCacheHandler().regFile(downloadUID(), filename)
					var sizeName: String = DownloadSystem.sizeToText(size)
					
					progressText = "文件创建完毕!准备传输数据(文件大小:${sizeName})..."
					
					var `in` = conn.inputStream
					var out = BufferedOutputStream(FileOutputStream(file))
					var nread = 0L
					val buf = ByteArray(81920)
					var n: Int
					var timestamp = System.currentTimeMillis() + 1000
					while (`in`.read(buf).also { n = it } > 0) {
						out.write(buf, 0, n)
						nread += n
						if (System.currentTimeMillis() >= timestamp) {
							timestamp += 1000
							progress = (nread * 99.0 / size + 1).toInt()
							var persent = String.format("%.2f", progress)
							progressText = "文件传输中(${persent}%) ${DownloadSystem.sizeToText(nread)}/$sizeName..."
						}
					}
					progressText = "下载完毕,导出缓冲流,关闭链接..."
					out.flush()
					out.close()
					`in`.close()
					progress = 100
					progressText = "文件\"${filename}\"下载完毕"
					result[FILE_SIZE] = size;
					result[FILE_NAME] = filename
					return 0
				} catch (e: IOException) {
					progressText = "文件下载失败"
					return 1
				}
			} else {
				progressText = "文件已找到,有相同的，无需下载"
				progress = 100
				return 0
			}
		}
	}
	
}