package name.soy.dc.tasks.exe

import name.soy.dc.tasks.Aligns
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class FileDownload : RemoteExecutable() {
    companion object {
        val URL = "url"
        val PATH = "to_file"

        val FILE_SIZE = "file_size"
        val FILE_NAME = "file_name"
    }

    override fun remoteParameters(): () -> HashMap<String, Aligns<*>> =  {
        HashMap<String, Aligns<*>>().apply {
            //目标地址,如:http://mapland.cn/download
            this[URL] = Aligns.createString(HashSet(), null, true,false)
            //下载到哪里,如D:\test.exe
            this[PATH] = Aligns.createString(HashSet(), null, true,false)
        }
    }

    override fun returnParameters() = HashMap<String, Class<*>>().apply {
        this[FILE_SIZE] = Int.javaClass
        this[FILE_NAME] = String.javaClass

    }

    override fun execute() = RemoteProgress(this)
    override fun localExecute() = LocalFileDownloadProgress(this)
    override fun getName() = "file download"

    class LocalFileDownloadProgress(exe: FileDownload) : Executable.ExecuteProgress(exe) {
        val KB = 1024L
        val MB = 1024L * 1024
        val GB = 1024L * 1024 * 1024
        val TB = 1024L * 1024 * 1024 * 1024
        fun sizeToText(size:Long)= when (size) {
            in 0..20 * KB -> "${String.format("%.2f",size)}B"
            in 20 * KB..MB -> "${String.format("%.2f",size.toDouble() / KB)}KB"
            in MB..GB -> "${String.format("%.2f",size.toDouble() / MB)}MB"
            in GB..TB -> "${String.format("%.2f",size.toDouble() / GB)}GB"
            else -> "${String.format("%.2f",size.toDouble() / TB)}TB"
        }
        override fun run(): Int {
            try {
                progressText = "正在请求http(s)数据..."
                var url = dataset[URL] as String
                var conn = URL(url).openConnection() as HttpURLConnection
                var size = conn.contentLengthLong
                progressText = "请求完毕!正在创建文件..."
                var file = File(dataset[PATH] as String)
                val fileParent: File = file.parentFile
                if (!fileParent.exists())
                    fileParent.mkdirs()
                if (!file.exists())
                    file.createNewFile()

                var sizeName: String = sizeToText(size)

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
                        progress = (nread*99.0/size+1).toInt()
                        var persent =String.format("%.2f",progress)
                        progressText = "文件传输中(${persent}%) ${sizeToText(nread)}/$sizeName..."
                    }
                }
                progressText = "下载完毕,导出缓冲流,关闭链接..."
                out.flush()
                out.close()
                `in`.close()
                progress = 100
                progressText = "文件${url.split("/").last()}下载完毕"
                resData =
                return 0
            } catch (e: IOException) {
                progressText = "文件下载失败"
                return 1
            } finally {

            }

        }
    }

}