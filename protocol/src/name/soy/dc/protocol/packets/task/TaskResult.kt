package name.soy.dc.protocol.packets.task

import name.soy.dc.protocol.packets.Packet
import java.util.*
import kotlin.collections.HashMap

class TaskResult:Packet {
    var resdata:HashMap<String,Any> = HashMap()
    var code:Int = 0
    var timestamp:Long = 0
    //task id,用于对应Task
    var TaskID: UUID = UUID.randomUUID()

    constructor()
    constructor(resdata: HashMap<String, Any>, code: Int, timestamp: Long, TaskID: UUID) {
        this.resdata = resdata
        this.code = code
        this.timestamp = timestamp
        this.TaskID = TaskID
    }

    override fun direction()= Packet.CLIENT_TO_SERVER
}