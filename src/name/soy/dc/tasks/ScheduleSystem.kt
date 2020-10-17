package name.soy.dc.tasks

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import java.util.*

@Manager("schedule")
class ScheduleSystem(val center: DataCenter) {
	var tasklist = HashMap<String, Task>()
}