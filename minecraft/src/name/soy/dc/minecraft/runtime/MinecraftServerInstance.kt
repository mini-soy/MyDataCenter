package name.soy.dc.minecraft.runtime

import name.soy.dc.protocol.data.minecraft.ServerType
import org.jetbrains.annotations.Nullable
import java.net.InetAddress

abstract class MinecraftServerInstance(override var name: String) : ServerInstance {
	
	abstract var type: ServerType
	
	/**
	 * 注意一下,BungeeCord支持多端口,所以使用InetAddress列表
	 */
	abstract fun getAddrs(): List<InetAddress>
	
	/**
	 * 是否远程服务器
	 */
	abstract fun isRemote(): Boolean
	
	/**
	 * 发送命令
	 * @return cmd 命令
	 */
	abstract fun sendCmd(cmd: String)
	
	
}