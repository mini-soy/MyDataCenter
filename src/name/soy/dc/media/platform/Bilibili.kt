package name.soy.dc.media.platform

import name.soy.dc.media.Platform
import name.soy.dc.media.PlatformData
import name.soy.dc.media.PlatformInterface

/**
 * 哔哩哔哩平台
 * UID:40082666
 */
class Bilibili : PlatformInterface {
	var uid = 40082666

	inner class BilibiliData : PlatformData() {
		/**
		 * 硬币数量
		 */
		var coins = 0

		/**
		 * 评论数量
		 */
		var reply = 0

		/**
		 * 弹幕数量
		 */
		var danmuku = 0
	}

	var yestoday: BilibiliData? = null
	override fun where(): Platform? {
		return Platform.BILIBILI
	}

	override fun fansUP(): Int {
		return 0
	}

	override val fans: Int
		get() = 0

	override fun playUP(): Int {
		return 0
	}

	override val play: Int
		get() = 0
	override val like: Int
		get() = 0

	override fun hasLike(): Boolean {
		return false
	}

	override val fav: Int
		get() = 0

	override fun hasFav(): Boolean {
		return false
	}

	override val income: Double
		get() = 0

	override fun fresh(): Boolean {
		return false
	}

	override fun vaild(): Boolean {
		return false
	}
}