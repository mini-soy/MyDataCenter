package name.soy.dc.minecraft.version

import name.soy.dc.minecraft.VersionManager

class FabricVersionLib(val manager: VersionManager) : VersionLib {
	companion object{
		val url = ""
	}
	@Volatile
	lateinit var fabricLoaderVersion:String
	@Volatile
	lateinit var vanillaVersion:String
	
	override fun checkForNew() {
	
	}
	
	override fun onNewVersion(version: String, new: Boolean) {
	
	}
}