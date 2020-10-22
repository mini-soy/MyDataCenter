package name.soy.dc.tasks.exe

import name.soy.dc.DataCenter
import name.soy.dc.client.Client
import name.soy.dc.protocol.packets.task.TaskCreate
import name.soy.dc.protocol.packets.task.TaskResult
import name.soy.dc.protocol.packets.task.TaskState
import name.soy.dc.tasks.Aligns

/**
 * 多台设备间的操作，注意，本地操作也要实现！！！
 */
abstract class RemoteExecutable : Executable {
    companion object{
        const val TARGET_DEVICE = "target_device"
    }
    final override fun needParameters(): () -> HashMap<String, Aligns<*>> = {
        HashMap<String, Aligns<*>>().apply {
            this[TARGET_DEVICE] = Aligns.createString(null, null, false)
            this.putAll(remoteParameters().invoke())
        }
    }

    abstract fun remoteParameters(): ()-> HashMap<String, Aligns<*>>

    override fun execute(): RemoteProgress = RemoteProgress(this)

    abstract fun localExecute(): Executable.ExecuteProgress

    open class RemoteProgress(override var exe: RemoteExecutable) : Executable.ExecuteProgress(exe) {
        private val lock = Object()
        private val result_packet:TaskResult? = null
        override fun run(): Int {
            var localexe = true
            var client:Client? = null
            if(dataset.containsKey(TARGET_DEVICE)) {
                var remoteaddr = dataset[TARGET_DEVICE] as String
                client = DataCenter.center!!.client()[remoteaddr]
                localexe = client.isLocal()
            }
            if (!localexe) {
                var localexe = exe.localExecute()
                localexe.setData(dataset)
                localexe.addProgressStatChangeListener {
                    progress = it.new.progress
                    stat = it.new.stat
                    progressText = it.new.text
                }
                localexe.addResultListener { it.resCode = resCode }
                return if (localexe.postRun()) {
                    localexe.monitor?.join()
                    resCode
                } else -1
            } else {
                val cr = TaskCreate(dataset,uid,exe.getName())
	            client!!.sendPacket(cr)
                synchronized(this) {
                    lock.wait()
                }
                resCode = result_packet!!.code
                return resCode
            }
        }
        operator fun plus(stat: TaskResult){
            synchronized(this){

                lock.notify()

            }
        }
        operator fun plus(stat: TaskState){

        }
    }
}