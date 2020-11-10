package name.soy.dc.minecraft.lib

interface VersionLib {
	
	fun checkForNew()
	
	fun onNewVersion(version: String,new: Boolean = true)
	
}