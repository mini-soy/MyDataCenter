package name.soy.dc.protocol.packets

class RegDevice : Packet {
    var deviceType: String = ""
    var aidaPort: Int = 0

    constructor()
    constructor(deviceType: String, aidaPort: Int) {
        this.deviceType = deviceType
        this.aidaPort = aidaPort
    }

    override fun direction(): Int = Packet.CLIENT_TO_SERVER
}