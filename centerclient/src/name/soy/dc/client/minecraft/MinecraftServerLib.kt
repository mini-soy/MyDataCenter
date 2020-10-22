package name.soy.dc.client.minecraft

import java.io.File

//负责管理MC服务器产品的库
class MinecraftServerLib(manager: MinecraftServerManager) {
    val libpath = File(manager.path,"library")
    init {
        if(!libpath.isDirectory)
            libpath.mkdir()
    }
    companion object {
        const val fabric_loader_url = ""

    }
}