package name.soy.dc.tasks

import name.soy.dc.tasks.exe.Executable
import java.util.*

class Task {
	var toexecute: Executable? = null

	/**
	 * 用于执行的数据
	 */
	var datas: HashMap<String, Any>? = null
}