package name.soy.dc.packets

val SERVER_TO_CLIENT = 1
val CLIENT_TO_SERVER = -1

val LEVEL_EVERYONE = 0;
val LEVEL_NEARBY_FIRENDS = 1;
val LEVEL_MY_ADMIN = 2;
val LEVEL_SYSTEM = 3;
val LEVEL_ONLY_ME = 4;
interface Packet {
	fun requireLevel(): Int
	fun direction() : Int
}