package name.soy.dc.task

import name.soy.dc.task.exe.Executable.ExecuteProgress


data class ExecuteProgressCallback(
		val old: Data,
		val new: Data,
		val prog: ExecuteProgress) {
	data class Data(
			val progress:Int,
			val text: String,
			val stat: ProgressStat)
	operator fun unaryPlus():Data = new
	
	operator fun unaryMinus():Data = old
	
	fun changedProgress(): Boolean = old.progress != new.progress

	fun changedText(): Boolean = old.text != new.text

	fun changedStat(): Boolean = old.stat != new.stat
}