package name.soy.dc.protocol.packets.task

import name.soy.dc.protocol.packets.Packet
import java.util.*

data class TaskState(
    //运行时间戳
    var timestamp: Long = 0,
    //task id,用于对应Task
    var TaskID: UUID = UUID.randomUUID(),
    //进度
    var progress: Int = 0,
    //显示文字
    var text: String = "",
    //状态
    var stat: Int = 0
) :Packet {
    override fun direction() = Packet.CLIENT_TO_SERVER
}