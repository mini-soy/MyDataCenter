package name.soy.dc.protocol.packets.minecraft

import name.soy.dc.protocol.PacketChannel
import name.soy.dc.protocol.packets.Packet

data class ServerCommand(
	var servers: ArrayList<String> = arrayListOf(),
	var command: String = ""
):Packet {
	override fun channel(): PacketChannel = PacketChannel.MINECRAFT_SERVER
	override fun direction() = Packet.SERVER_TO_CLIENT
}