package name.soy.dc.protocol.packets.task

import name.soy.dc.protocol.packets.Packet
import java.util.*

class TaskState:Packet {
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

    constructor(timestamp: Long, TaskID: UUID, progress: Double, text: String, stat: Int) {
        this.timestamp = timestamp
        this.TaskID = TaskID
        this.progress = progress
        this.text = text
        this.stat = stat
    }

    constructor()

    override fun direction() = Packet.CLIENT_TO_SERVER

}