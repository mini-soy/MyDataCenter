package name.soy.dc.client

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.socket.SocketChannel
import name.soy.dc.packets.Login
import name.soy.dc.packets.PacketManager

class ClientHello(val socket: SocketChannel, val manager: ClientManager): ChannelInboundHandlerAdapter() {

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        val packet = PacketManager.readObject(msg as ByteBuf)
        if(packet is Login){
            packet.private.contentEquals(manager.center.privateData.deviceVerifyData())
            socket.pipeline().remove(this)
            socket.pipeline().addLast(Client(packet.name,manager))
        }
        super.channelRead(ctx, msg)
    }
}