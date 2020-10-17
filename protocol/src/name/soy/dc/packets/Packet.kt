package name.soy.dc.packets

import java.io.Serializable

interface Packet:Serializable {
    fun direction(): Int

    fun channel(): PacketChannel = PacketChannel.UNIVERSE

    companion object {
        const val SERVER_TO_CLIENT = 1
        const val CLIENT_TO_SERVER = -1
    }
}