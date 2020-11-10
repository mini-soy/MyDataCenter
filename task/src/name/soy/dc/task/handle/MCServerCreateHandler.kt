package name.soy.dc.task.handle

import java.io.File

interface MCServerCreateHandler {
	fun spigotBuildFolder(): File
	
	companion object {
		private lateinit var creator: MCServerCreateHandler
		fun handle(cache: MCServerCreateHandler) {
			Companion.creator = cache
		}
		
		operator fun invoke() = creator
	}
}