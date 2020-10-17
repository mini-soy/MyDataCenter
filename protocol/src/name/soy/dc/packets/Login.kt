package name.soy.dc.packets

class Login:Packet {
    var name:String = ""
    var private:ByteArray = byteArrayOf()
    override fun direction() = Packet.SERVER_TO_CLIENT

}