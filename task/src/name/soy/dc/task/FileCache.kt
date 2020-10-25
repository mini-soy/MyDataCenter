package name.soy.dc.task

import name.soy.dc.task.exe.FileDownload

/**
 * @author soy
 * @throws OutOfMemoryError
 * @version 1.0
 * @sample OutOfMemoryError.printStackTrace
 */
interface FileCache {
	/**
	 * 添加FileDownloadTask
	 */
	operator fun plus(prog: FileDownload.LocalFileDownloadProgress) = post(prog)
	fun post(prog: FileDownload.LocalFileDownloadProgress)
	/**
	 * 获取UID的下载状态
	 */
	operator fun get(uid:String):Int
	
	companion object{
		private lateinit var cache:FileCache
		fun handle(cache:FileCache) {
			this.cache=cache
		}
		operator fun invoke() = cache
	}
}

/**

  *************
  *           *
  *   R I P   *
  *           *
  *************

 */
