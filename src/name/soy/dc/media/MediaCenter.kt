package name.soy.dc.media

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import java.util.*

@Manager("media")
class MediaCenter(dataCenter: DataCenter?) {
	var alldatas: List<PlatformData> = ArrayList()
}