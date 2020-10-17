package name.soy.dc.packets.task

import io.netty.buffer.ByteBuf
import name.soy.dc.packets.Packet
import java.util.*

class TaskStat:Packet {
    //运行时间戳
    var timestamp: Long = 0
    //task id,用于对应Task
    var TaskID:UUID = UUID.randomUUID()
    //进度
    var progress:Double = 0.0
    //显示文字
    var text: String = ""
    //状态
    var stat: Int = 0

    override fun direction() = Packet.CLIENT_TO_SERVER

}