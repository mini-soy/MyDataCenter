package name.soy.dc.minecraft

import lombok.AllArgsConstructor
import lombok.Getter


enum class ServerType(val parent: ServerType?, val dis: String, val centent: Boolean, val proxy: Boolean) {
	VANILLA(null, "vanilla", true, false),
	FABRIC(VANILLA, "fabric", true, false),
	BUKKIT(VANILLA, "craftbukkit", true, false),
	SPIGOT(BUKKIT, "spigot", true, false),
	PAPER(SPIGOT, "paper", true, false),
	FORGE(VANILLA, "forge", true, false),
	BUNGEECORD(null, "BungeeCord", false, true),
	WATERFALL(BUNGEECORD, "WaterFall", false, true),
	SPONGE(VANILLA, "sponge", true, false),
	VELOCITY(null, "celocity", false, true);





}