package name.soy.dc.tasks

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import name.soy.dc.task.RemoteHandler
import name.soy.dc.tasks.exe.RemoteHandle
import java.util.*

@Manager("schedule")
class ScheduleSystem(val center: DataCenter) {
	init {
		//添加链接
		RemoteHandler.addRemoteHandle(::RemoteHandle)
	}
	var tasklist = HashMap<String, Task>()
}