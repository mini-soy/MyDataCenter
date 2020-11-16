package name.soy.dc.minecraft.util

enum class PropertiesUtils(default:Any,commit:String="暂无说明",dot_spilt:Boolean = false) {
	
	SPAWN_PROTECTION(16,"出生点保护(单位:区块)"),
	
	MAX_TICK_TIME(60000,"最大的tick周期(单位ms)，一旦单tick运行超过了时间，则服务器崩溃"),
	
	QUERY_PORT(25565,"查询端口",true),
	
	GENERATOR_SETTINGS(""),
	
	SYNC_CHUNK_WRITES(true),
	
	FORCE_GAMEMODE(false),
	
	ALLOW_NETHER(false),
	
	;
}