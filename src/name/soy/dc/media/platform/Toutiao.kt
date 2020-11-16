package name.soy.dc.media.platform

import name.soy.dc.media.Platform
import name.soy.dc.media.PlatformInterface

abstract class Toutiao : PlatformInterface {
	override fun where(): Platform = Platform.XIGUA
	
}