package name.soy.dc.tasks.exe

import name.soy.dc.tasks.exe.Executable.ExecuteProgress


data class ExecuteProgressCallback(val old: Data, val new: Data,val prog: ExecuteProgress) {
	data class Data(val progress:Int
	          ,val text: String
	          ,val stat: ProgressStat)

	fun changedProgress(): Boolean = old.progress != new.progress

	fun changedText(): Boolean = old.text != new.text

	fun changedStat(): Boolean = old.stat != new.stat
}