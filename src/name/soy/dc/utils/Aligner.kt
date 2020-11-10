package name.soy.dc.utils

import name.soy.dc.task.Aligns

abstract class Aligner(
	val data: HashMap<String, Aligns<*>> = HashMap()
) {
	abstract fun alignDataGet(values:HashMap<String, Any>)
	
	fun reCallback(){

	}
}