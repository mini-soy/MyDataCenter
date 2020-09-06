package name.soy.dc.packets

data class Command(val cmd: String) : Packet {
	override fun requireLevel() = LEVEL_SYSTEM;

	override fun direction() = SERVER_TO_CLIENT
	
}