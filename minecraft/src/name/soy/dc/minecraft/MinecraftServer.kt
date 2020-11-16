package name.soy.dc.minecraft

import name.soy.dc.minecraft.handle.IMinecraftServerManager
import name.soy.dc.minecraft.runtime.DedicatedServerInstance
import name.soy.dc.minecraft.runtime.MinecraftServerInstance
import name.soy.dc.protocol.data.minecraft.ServerType
import java.io.File

class MinecraftServer(
		private val manager: IMinecraftServerManager,
		val name: String,
		var serverType: ServerType,
		var version: String,
		var path: File = manager.serverPath(),
		/**自定义启动命令，如果为空会根据参数自动生成*/
		var customstartUpCmd: String? = null,
) {
	private fun getDefaultStartUpCmd(): String =""
	
	/**单位:MB*/
	var maxMemory: Int = 2048;
	
	/**额外的参数*/
	var arguments: List<String> = arrayListOf();
	
	/**服务器实例，可能不存在*/
	@Transient
	var instance: DedicatedServerInstance? = null
	
	fun start() {
		if (instance != null)
			throw IllegalAccessException("实例已经存在，请勿重复创建")
		instance = DedicatedServerInstance(
				manager,
				this,
				ProcessBuilder().command(
					if (customstartUpCmd == null)
						getDefaultStartUpCmd()
					else
						customstartUpCmd).
				directory(path).
				start()
		).apply {
			manager.serverListeners.forEach { it.onServerStart(this) }
		}
		
	}
}