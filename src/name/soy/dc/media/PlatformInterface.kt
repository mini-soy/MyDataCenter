package name.soy.dc.media

interface PlatformInterface {
	fun where(): Platform?

	/**
	 *
	 * @return 昨日增加的粉丝数量
	 */
	fun fansUP(): Int

	/**
	 *
	 * @return 总粉丝数
	 */
	var fans: Int

	/**
	 *
	 * @return 昨日的播放量
	 */
	fun playUP(): Int

	/**
	 *
	 * @return 总播放量
	 */
	var play: Int

	/**
	 *
	 * @return
	 */
	var like: Int
	fun hasLike(): Boolean
	var fav: Int
	fun hasFav(): Boolean
	var income: Double
	fun fresh(): Boolean
	fun vaild(): Boolean
}