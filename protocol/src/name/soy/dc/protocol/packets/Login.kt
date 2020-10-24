package name.soy.dc.protocol.packets

class Login(
        var name: String = "",
        var private: ByteArray = byteArrayOf()
) :Packet {
    override fun direction() = Packet.SERVER_TO_CLIENT
}