package name.soy.dc.protocol.packets.minecraft

import name.soy.dc.protocol.PacketChannel
import name.soy.dc.protocol.packets.Packet

data class ServerLog(
	var serverName: String = "",
	var lineData: String = ""
) : Packet {
	override fun channel(): PacketChannel = PacketChannel.MINECRAFT_SERVER
	
	override fun direction() = Packet.CLIENT_TO_SERVER
}