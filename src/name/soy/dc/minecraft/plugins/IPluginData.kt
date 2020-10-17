package name.soy.dc.minecraft.plugins

import name.soy.dc.minecraft.ServerType

interface IPluginData {
	/**
	 *
	 * @return 插件名称(也可以是服务端mod)
	 */
	fun name(): String?

	/**
	 * 兼容的服务端列表
	 */
	fun compatibly(): List<ServerType?>?

	/**
	 * 插件描述
	 * @return
	 */
	fun dis(): String?

	/**
	 * 插件的ci地址，有ci地址就可以自动更新插件
	 * @return
	 */
	fun CIAddress(): String?

	/**
	 * github地址，如果说要更新就可以自动构建(想peach
	 * @return
	 */
	fun githubAddress(): String?

	/**
	 * 是否开源
	 * @return
	 */
	val isOpenSource: Boolean
}