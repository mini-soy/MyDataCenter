package name.soy.dc.packets.minecraft

import name.soy.dc.packets.Packet

class ServerInfo : Packet {



    override fun direction()=Packet.CLIENT_TO_SERVER

}