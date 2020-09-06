package name.soy.dc.aida

enum class AIDAEntry(name:String,unit:String,modifiler:Class<Any>){
	CPU_USAGE("CPU使用率","%",Unit.javaClass),
	CPU_FREQUENCY("CPU频率","MHz",Unit.javaClass),
	MEMORY_USAGE("内存使用率","%",Unit.javaClass),
	
	
	;
}