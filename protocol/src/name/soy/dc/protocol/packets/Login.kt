package name.soy.dc.protocol.packets

class Login:Packet {
    var name:String = ""
    var private:ByteArray = byteArrayOf()
    override fun direction() = Packet.SERVER_TO_CLIENT

}