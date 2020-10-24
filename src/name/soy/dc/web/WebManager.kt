package name.soy.dc.web

import name.soy.dc.annotation.Manager
import java.util.*

@Manager("web")
class WebManager {
	/**
	 * 侧边栏列表
	 */
	var sidebarUrls = LinkedList<SideBar>()

}