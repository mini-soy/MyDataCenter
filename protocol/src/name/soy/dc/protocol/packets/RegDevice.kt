package name.soy.dc.protocol.packets

data class RegDevice(
        var deviceType: String = "",
        var aidaPort: Int = 0
) : Packet {
    override fun direction(): Int = Packet.CLIENT_TO_SERVER
}