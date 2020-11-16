package name.soy.dc.minecraft

data class Mirror(val mirrorname:String,var replace:List<Pair<String,String>>) {
	companion object{
		val NO_MIRROR = Mirror("none", arrayListOf<Pair<String,String>>())
		val BMCL_API = Mirror("bmcl", arrayListOf<Pair<String,String>>().apply {
			add(Pair("http://resources.download.minecraft.net","https://bmclapi2.bangbang93.com/assets"))
			add(Pair("https://libraries.minecraft.net", "https://bmclapi2.bangbang93.com/maven"))
			add(Pair("https://meta.fabricmc.net", "https://bmclapi2.bangbang93.com/fabric-meta"))
			add(Pair("https://maven.fabricmc.net", "https://bmclapi2.bangbang93.com/maven"))
		})
		val MCBBS_API = Mirror("mcbbs",arrayListOf<Pair<String,String>>().apply {
			add(Pair("http://resources.download.minecraft.net","https://download.mcbbs.net/assets"))
			add(Pair("https://libraries.minecraft.net", "https://download.mcbbs.net/maven"))
			add(Pair("https://meta.fabricmc.net", "https://download.mcbbs.net/fabric-meta"))
			add(Pair("https://maven.fabricmc.net", "https://download.mcbbs.net/maven"))
		})
		val mirrors = arrayListOf<Mirror>(NO_MIRROR, BMCL_API, MCBBS_API)
	}
	operator fun rangeTo(url: String):String {
		var replaced:String = url;
		for(p in replace)
			replaced = url.replace(p.first,p.second);
		return replaced;
	}
}