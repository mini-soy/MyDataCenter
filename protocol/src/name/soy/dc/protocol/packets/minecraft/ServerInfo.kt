package name.soy.dc.protocol.packets.minecraft

import name.soy.dc.protocol.data.minecraft.ServerType
import name.soy.dc.protocol.packets.Packet

class ServerInfo : Packet {
	/**
	 * 服务器名称
	 */
	var serverName: String = ""

	/**
	 * 在线人数
	 */
	var online: Int = 0

	/**
	 * 内存使用(MB)
	 */
	var memory: Double = 0.0

    /**
     * 服务器类型
     */
	var type = ServerType.UNKNOWN


	override fun direction() = Packet.CLIENT_TO_SERVER
}