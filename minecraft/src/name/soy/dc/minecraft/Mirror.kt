package name.soy.dc.minecraft

data class Mirror(val mirrorname:String,var replace: (String) -> String) {
	companion object{
		val NO_MIRROR = Mirror("none") { it }
		val BMCL_API = Mirror("bmcl") {
			//assest
			it.replace("http://resources.download.minecraft.net", "https://bmclapi2.bangbang93.com/assets")
					//library
					.replace("https://libraries.minecraft.net", "https://bmclapi2.bangbang93.com/maven")
					//fabric
					.replace("https://meta.fabricmc.net", "https://bmclapi2.bangbang93.com/fabric-meta")
					//fabric
					.replace("https://maven.fabricmc.net", "https://bmclapi2.bangbang93.com/maven")
		}
		val MCBBS_API = Mirror("mcbbs") {
			BMCL_API.replace(it).replace("https://bmclapi2.bangbang93.com", "https://download.mcbbs.net")
		}
		val mirrors = arrayListOf(NO_MIRROR, BMCL_API, MCBBS_API)
	}
	
	operator fun rangeTo(url: String) = replace(url)
}