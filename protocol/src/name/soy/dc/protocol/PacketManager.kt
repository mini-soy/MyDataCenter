package name.soy.dc.protocol

import io.netty.buffer.ByteBuf

import java.io.*

class PacketManager {
	companion object{
		fun encodeObject(value: Serializable, buf: ByteBuf) =
			with(buf) {
				val out = ByteArrayOutputStream()
				val o_out = ObjectOutputStream(out)
				o_out.writeObject(value)
				o_out.flush()
				val buffer = out.toByteArray()
				writeInt(buffer.size)
				writeBytes(buffer)
			}

		fun readObject(buf: ByteBuf): Serializable {
			val readcount = buf.readInt()
			val buffer = ByteArray(readcount)
			buf.readBytes(buffer)
			val `in` = ByteArrayInputStream(buffer)
			val o_in = ObjectInputStream(`in`)
			return o_in.readObject() as Serializable
		}
		fun receivePacket(handler: IClientHandler, buf: ByteBuf){
			val obj = readObject(buf)
			handler.javaClass.getMethod("onPacket",obj.javaClass).invoke(handler,obj)
		}
	}
}