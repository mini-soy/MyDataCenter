package name.soy.dc.task.handle

import name.soy.dc.task.exe.FileDownload
import java.io.File
import java.io.FileInputStream
import java.io.FileReader

interface FileCacheHandler {
	/**
	 * 添加FileDownloadTask
	 */
	operator fun plus(prog: FileDownload.LocalFileDownloadProgress) = post(prog)
	
	fun post(prog: FileDownload.LocalFileDownloadProgress)
	
	/**
	 * 获取UID的下载状态
	 */
	operator fun get(uid:String):Int
	
	operator fun times(uid:String): File = getFile(uid)
	
	/**
	 * (*)
	 * 通过UID获取文件对象
	 */
	fun getFile(uid:String): File
	
	
	operator fun minus(uid:String): FileInputStream = getStream(uid)
	/**
	 * (-)
	 * 通过UID获取文件流
	 */
	fun getStream(uid:String): FileInputStream
	
	/**
	 * 是否需要下载?
	 */
	fun needDownload(uid: String): Boolean
	
	/**
	 * 创建一个对于UID,下载的文件环境
	 * 返回之前文件一定可见
	 */
	fun regFile(uid: String, fileName:String): File
	
	companion object{
		private lateinit var cache: FileCacheHandler
		fun handle(cache: FileCacheHandler) {
			Companion.cache = cache
		}
		operator fun invoke() = cache
	}
}
