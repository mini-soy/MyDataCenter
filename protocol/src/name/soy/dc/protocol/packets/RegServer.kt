package name.soy.dc.protocol.packets
class RegServer : Packet {

	override fun direction(): Int = Packet.CLIENT_TO_SERVER

}