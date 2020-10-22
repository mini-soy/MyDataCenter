package name.soy.dc.protocol.packets.task

import name.soy.dc.protocol.packets.Packet
import java.util.*
import kotlin.collections.HashMap

class TaskCreate:Packet {
    //创建任务所需要的数据
    var dataset:HashMap<String,Any> = HashMap()
    //任务id
    var taskID:UUID = UUID.randomUUID()
    //任务名称(判定)
    var taskType:String = ""

    constructor()
    constructor(dataset: HashMap<String, Any>, taskID: UUID, taskType: String) {
        this.dataset = dataset
        this.taskID = taskID
        this.taskType = taskType
    }

    override fun direction()= Packet.SERVER_TO_CLIENT

}