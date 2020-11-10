package name.soy.dc.media.platform

import name.soy.dc.media.Platform
import name.soy.dc.media.PlatformInterface

class Toutiao : PlatformInterface {
	override fun where(): Platform = Platform.XIGUA
	
	override fun fansUP(): Int {
		TODO("Not yet implemented")
	}
	
	override val fans: Int = 0
	
	override fun playUP(): Int {
		TODO("Not yet implemented")
	}
	
	override val play: Int
		get() = TODO("Not yet implemented")
	override val like: Int
		get() = TODO("Not yet implemented")
	
	override fun hasLike(): Boolean {
		TODO("Not yet implemented")
	}
	
	override val fav: Int
		get() = TODO("Not yet implemented")
	
	override fun hasFav(): Boolean {
		TODO("Not yet implemented")
	}
	
	override val income: Double
		get() = TODO("Not yet implemented")
	
	override fun fresh(): Boolean {
		TODO("Not yet implemented")
	}
	
	override fun vaild(): Boolean {
		TODO("Not yet implemented")
	}
}