package name.soy.dc.protocol.packets.minecraft

import name.soy.dc.protocol.packets.Packet

class ServerLog: Packet {
	var serverName:String = ""

	var lineData:String = ""

	override fun direction() = Packet.CLIENT_TO_SERVER

}