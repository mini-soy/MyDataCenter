package name.soy.dc.tasks

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import name.soy.dc.task.handle.FileCacheHandler
import name.soy.dc.task.handle.RemoteHandler
import name.soy.dc.tasks.exe.CloudFileCache
import name.soy.dc.tasks.exe.CloudRemoteHandle
import java.io.File
import java.util.*

@Manager("schedule")
class ScheduleSystem(val center: DataCenter) {
	
	init {
		//添加链接
		RemoteHandler.addRemoteHandle(::CloudRemoteHandle)
		var a: () -> ()->()->()->()->()->Int = {{ { { { { 1 } } } } } }
		FileCacheHandler.handle(CloudFileCache(File("C:\\download-cache")))
	}
	
	operator fun get(se: ScheduleSystem) = this;
	operator fun invoke() = this;
	var tasklist = HashMap<String, Task>()
}