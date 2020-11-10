package name.soy.dc.task.exe

import name.soy.dc.task.Aligns
import name.soy.dc.task.exe.Executable.ExecuteProgress
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader

open class Command : RemoteExecutable() {

	override fun remoteParameters(): () -> HashMap<String, Aligns<*>> =  {
		HashMap<String, Aligns<*>>().apply {
			this["command"] = Aligns.createString(hashSetOf(), null, true)
			this["folder"] = Aligns.createString(hashSetOf(), null, true)
		}
	}

	override fun getName() = "run command"

	override fun returnParameters(): () -> HashMap<String, Class<*>> = {
		HashMap<String, Class<*>>().apply {
			this["execute_time"] = Int::class.java
			this["read_data"] = String::class.java
		}
	}

	override fun localExecute(): ExecuteProgress = LocalCommandRun(this)


	class LocalCommandRun(exe: Executable) : ExecuteProgress(exe) {
		override fun run(): Int {
			var exec:Process
			try {
				val cmd = dataset["command"] as String
				val startTime = System.currentTimeMillis()
				exec = ProcessBuilder(cmd)
						.directory(File(dataset["folder"] as String))
						.start()
				val `in`: Reader = InputStreamReader(exec.inputStream, "GBK")
				val sb = StringBuilder()
				while (true) {
					val c:Int = `in`.read()
					if (c == -1) break
					sb.append(c.toChar())
				}
				val endTime = System.currentTimeMillis()
				result["execute_time"] = endTime-startTime
				result["read_data"] = sb.toString()
			} catch (e: IOException) {
				e.printStackTrace()
				return -1
			}
			return exec.exitValue()
		}
	}
}