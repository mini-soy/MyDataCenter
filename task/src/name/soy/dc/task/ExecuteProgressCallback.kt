package name.soy.dc.task

import name.soy.dc.task.exe.Executable.ExecuteProgress


data class ExecuteProgressCallback(
		val old: Data,
		val new: Data,
		val prog: ExecuteProgress,
) {
	data class Data(
			val progress: Int,
			val text: String,
			val stat: ProgressStat,
	)
	
	operator fun unaryPlus(): Data = new
	
	operator fun unaryMinus(): Data = old
	
	fun changedProgress() = old.progress != new.progress
	
	fun changedText() = old.text != new.text
	
	fun changedStat() = old.stat != new.stat
}