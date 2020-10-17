package name.soy.dc.packets

enum class PacketChannel(val parent: PacketChannel?) {
	UNIVERSE(null),
	DEVICE(UNIVERSE),
	MINECRAFT_SERVER(UNIVERSE),
	PHONE(UNIVERSE);
}