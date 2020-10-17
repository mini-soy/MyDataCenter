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
	val fans: Int

	/**
	 *
	 * @return 昨日的播放量
	 */
	fun playUP(): Int

	/**
	 *
	 * @return 总播放量
	 */
	val play: Int

	/**
	 *
	 * @return
	 */
	val like: Int
	fun hasLike(): Boolean
	val fav: Int
	fun hasFav(): Boolean
	val income: Double
	fun fresh(): Boolean
	fun vaild(): Boolean
}