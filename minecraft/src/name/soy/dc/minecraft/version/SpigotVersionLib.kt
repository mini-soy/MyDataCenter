package name.soy.dc.minecraft.version

import name.soy.dc.minecraft.VersionManager
import org.jsoup.Jsoup
import java.util.*

class SpigotVersionLib(val manager: VersionManager) : VersionLib {
	data class SpigotVersion(var version: String, val time: Date)
	companion object {
		const val url = "https://hub.spigotmc.org/versions/"
	}
	
	/**
	 * 最新的构建版本
	 */
	@Volatile
	var newestVersion: String = ""
	
	/**
	 * latest.json
	 */
	@Volatile
	var latestVersion: String = ""
	
	/**
	 * 所有可用版本
	 */
	@Volatile
	var versions = arrayListOf<SpigotVersion>()
	
	var inited = false;
	override fun checkForNew() {
		val dom = Jsoup.connect("https://hub.spigotmc.org/versions/").execute().parse()
		val body = dom.getElementsByTag("pre")[0]
		val text = body.text()
		var a_version_text = text.split("\n").filterIndexed { index, s -> index != 0 };
		for (it in a_version_text) {
			var n = 0
			var version = ""
			var cale = Calendar.getInstance()
			cale.timeInMillis = 0;
			for (s in it.split("  ")) {
				if (s.isBlank()) continue
				when (n) {
					0 -> version = s
					//大佬救我！
					1 -> {
						var str = s.trim();
						
						var dd_mm_yyyy = str.split(" ")[0].split("-")
						var hh_mm = str.split(" ")[1].split(":")
						
						var mm = dd_mm_yyyy[1].replace("Jan", "00")
								.replace("Feb", "01")
								.replace("Mar", "02")
								.replace("Apr", "03")
								.replace("May", "04")
								.replace("Jun", "05")
								.replace("Jul", "06")
								.replace("Aug", "07")
								.replace("Sep", "08")
								.replace("Oct", "09")
								.replace("Nov", "10")
								.replace("Dec", "11")
						cale.set(dd_mm_yyyy[2].toInt(), mm.toInt(), dd_mm_yyyy[0].toInt(), hh_mm[0].toInt(),
								hh_mm[1].toInt())
					}
				}
				n++;
			}
			versions.add(SpigotVersion(version, cale.time))
		}
	}
	
	override fun onNewVersion(version: String, new: Boolean) {
		TODO("Not yet implemented")
	}
}