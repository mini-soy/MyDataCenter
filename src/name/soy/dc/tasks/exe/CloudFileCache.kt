package name.soy.dc.tasks.exe

import name.soy.dc.DataCenter
import name.soy.dc.log.LogEntry
import name.soy.dc.task.exe.FileDownload
import name.soy.dc.task.handle.FileCacheHandler
import java.io.File
import java.util.logging.Level

class CloudFileCache(val cacheDir: File) : FileCacheHandler {
	private val regFiles = hashMapOf<String, File>()
	private val fileMap = hashMapOf<String, File>()
	
	private val nowProgresses = hashMapOf<String, FileDownload.LocalFileDownloadProgress>()
	
	/**
	 * 这里报告文件下载失败
	 */
	override fun post(prog: FileDownload.LocalFileDownloadProgress) {
		val uid = prog.downloadUID()
		nowProgresses[uid] = prog;
		prog.addResultListener {
			if (prog.resCode == 1) {
				DataCenter().log().log(LogEntry.TASK, Level.WARNING, prog.progressText)
			} else if (prog.resCode == 0) {
				regFiles[uid]?.let { it1 ->
					fileMap.put(uid, it1)
				}
				regFiles.remove(uid)
				nowProgresses.remove(uid)
			}
		}
	}
	
	/**
	 * 获取UID的下载状态
	 */
	override fun get(uid: String): Int {
		return when {
			fileMap.containsKey(uid) -> 100
			nowProgresses.containsKey(uid) -> nowProgresses[uid]!!.progress
			else -> -1
		}
	}
	
	/**
	 * (*)
	 * 通过UID获取文件对象
	 */
	override fun getFile(uid: String) = fileMap[uid]!!
	
	/**
	 * (-)
	 * 通过UID获取文件流
	 */
	override fun getStream(uid: String) = fileMap[uid]!!.inputStream()
	
	/**
	 * 是否需要下载?
	 */
	override fun needDownload(uid: String): Boolean {
		return nowProgresses.containsKey(uid)||fileMap.containsKey(uid)
	}
	
	/**
	 * 创建一个对于UID,下载的文件环境
	 * 返回之前文件一定可见
	 */
	override fun regFile(uid: String, fileName: String): File {
		var dirName: String = uid.substring(0..uid.lastIndexOf('-'))
		var dir = File(cacheDir, dirName)
		if (!dir.isDirectory)
			dir.mkdir()
		return File(dir, fileName).apply {
			if (!exists())
				createNewFile()
			regFiles[uid] = this;
		}
	}
}