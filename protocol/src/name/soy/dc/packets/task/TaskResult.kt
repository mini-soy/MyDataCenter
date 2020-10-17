package name.soy.dc.packets.task

import io.netty.buffer.ByteBuf
import name.soy.dc.packets.Packet
import java.util.*
import kotlin.collections.HashMap

class TaskResult:Packet {
    var resdata:HashMap<String,Any> = HashMap()
    var code:Int = 0
    var timestamp:Long = 0
    //task id,用于对应Task
    var TaskID: UUID = UUID.randomUUID()
    override fun direction()= Packet.CLIENT_TO_SERVER
}