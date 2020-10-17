package name.soy.dc.tasks.exe

import name.soy.dc.tasks.Aligns
import name.soy.dc.tasks.exe.Executable.ExecuteProgress
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader

class Command : RemoteExecutable() {

    override fun remoteParameters(): () -> HashMap<String, Aligns<*>> =  {
        HashMap<String, Aligns<*>>().apply {
            this["command"] = Aligns.createString(null,null,true)
        }
    }
    override fun getName() = "run command"
    override fun returnParameters(): HashMap<String, Class<*>> =  HashMap<String, Class<*>>().apply {
        this["execute_time"] = Int.javaClass
        this["exit_code"] = Int.javaClass
        this["read_data"] = String.javaClass
    }
    override fun execute(): RemoteProgress = RemoteProgress(this)
    override fun localExecute(): ExecuteProgress = LocalCommandRun(this)


    class LocalCommandRun(exe: Executable) : ExecuteProgress(exe) {
        override fun run(): Int {
            var exec:Process;
            try {
                val cmd = dataset["command"] as String
                val startTime = System.currentTimeMillis()
                exec = Runtime.getRuntime().exec(cmd)
                val `in`: Reader = InputStreamReader(exec.inputStream, "GBK")
                val sb = StringBuilder()
                while (true) {
                    val c = `in`.read()
                    if (c == -1) break
                    sb.append(c.toChar())
                }
                val endTime = System.currentTimeMillis()

            } catch (e: IOException) {
                e.printStackTrace()
                return -1;
            }
            return exec.exitValue();
        }
    }
}