package name.soy.dc.protocol.packets.task

import name.soy.dc.protocol.packets.Packet
import java.util.*
import kotlin.collections.HashMap

data class TaskPost(
//	//创建任务所需要的数据
//	var dataset: HashMap<String, Any> = HashMap(),
	//任务id
	var taskID: UUID = UUID.randomUUID(),
	//任务名称(判定)
	var taskType: String = ""
): Packet {
	override fun direction(): Int = Packet.CLIENT_TO_SERVER
}