package name.soy.dc.client

import io.netty.channel.ChannelInboundHandlerAdapter
import name.soy.dc.client.IClient
import name.soy.dc.packets.IClientHandler
import java.lang.Exception
import io.netty.channel.ChannelHandlerContext
import io.netty.buffer.ByteBuf
import name.soy.dc.device.IDevice
import name.soy.dc.packets.Packet
import name.soy.dc.packets.PacketManager
import name.soy.dc.packets.RegDevice
import name.soy.dc.packets.minecraft.ServerData
import name.soy.dc.packets.minecraft.ServerInfo
import name.soy.dc.packets.minecraft.ServerLog
import name.soy.dc.packets.task.TaskCreate
import name.soy.dc.packets.task.TaskResult
import name.soy.dc.packets.task.TaskStat

/**
 * 一个客户端实例
 */
open class Client(override val name:String, private val manager: ClientManager) : ChannelInboundHandlerAdapter(), IClient, IClientHandler {
    /**
     * 收到数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Throws(Exception::class)
    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        PacketManager.receivePacket(this, msg as ByteBuf)
    }

    override fun getDevice(): IDevice? {
        TODO("Not yet implemented")
    }


    override fun canPostTask(): Boolean {
        return false
    }


    override fun isLocal()= false

    override fun sendPacket(packet: Packet) {}

    override fun onPacket(packet: RegDevice) {}

    override fun onPacket(packet: TaskCreate) {}

    override fun onPacket(packet: TaskStat) {}

    override fun onPacket(packet: TaskResult) {}

    override fun onPacket(packet: ServerData) {}

    override fun onPacket(packet: ServerInfo) {}

    override fun onPacket(packet: ServerLog) {}
}