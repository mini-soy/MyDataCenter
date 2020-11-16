package name.soy.dc.minecraft.version

interface VersionLib {
	
	fun checkForNew()
	
	fun onNewVersion(version: String,new: Boolean = true)
	
	
}