package name.soy.dc.minecraft.handle

import name.soy.dc.minecraft.VersionManager
import name.soy.dc.minecraft.version.VanillaVersionLib

interface VersionListener {
	var manager:VersionManager
	
	fun onNewVanillaVersion(version:VanillaVersionLib.VanillaVersion)
	
	fun onNewFabricVersion(version:VanillaVersionLib.VanillaVersion,new: Boolean)
}