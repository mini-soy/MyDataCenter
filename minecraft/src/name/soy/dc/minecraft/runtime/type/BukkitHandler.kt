package name.soy.dc.minecraft.runtime.type

import name.soy.dc.minecraft.runtime.DedicatedServerInstance
import java.net.InetAddress
import java.util.*

class BukkitHandler(override val instance: DedicatedServerInstance) : VanillaHandler(instance) {
	
	override fun isWhitelisted() {
	
	}
	
	override fun getProperties(): Properties {
		TODO("Not yet implemented")
	}
	
	override fun getAddrs(): List<InetAddress> {
		TODO("Not yet implemented")
	}
	
}
