package name.soy.dc.minecraft.runtime.type

import name.soy.dc.minecraft.runtime.DedicatedServerInstance
import java.net.InetAddress
import java.util.*

open class VanillaHandler(override val instance: DedicatedServerInstance) : RuntimeHandler {
	private val properties:Properties = Properties();
	//temp:临时的
	protected var tempWhitelist = false;
	
	protected var tempPort = -1;
	protected var tempLocalhost = false;
	init{
		var propertiesFile = file("server.properties");
		if(!propertiesFile.exists()){
		
		}
		
	}
	override fun getProperties(): Properties = properties;
	
	override fun stop() = instance.sendCmd("stop");
	
	override fun isWhitelisted() {
	
	}
}