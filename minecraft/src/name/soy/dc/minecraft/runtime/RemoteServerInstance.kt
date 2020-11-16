package name.soy.dc.minecraft.runtime

import name.soy.dc.protocol.data.minecraft.ServerType
import java.net.InetAddress

class RemoteServerInstance(
		name: String,
        override var type: ServerType,
) : MinecraftServerInstance(name) {
	
	override fun getAddrs(): List<InetAddress> {
		TODO("Not yet implemented")
	}
	
	override fun isRemote() = true;
	
	override fun sendCmd(cmd: String) {
		TODO("Not yet implemented")
	}
	
}