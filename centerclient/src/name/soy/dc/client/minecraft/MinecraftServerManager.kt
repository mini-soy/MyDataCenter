package name.soy.dc.client.minecraft

import name.soy.dc.client.CenterClient
import java.io.File
import java.io.FileReader
import java.util.*

class MinecraftServerManager(center: _root_ide_package_.name.soy.dc.client.CenterClient) {
    var properties: Properties = Properties()
    var path: File
    var lib: MinecraftServerLib

    init{
        File("mcsm.properties").apply {
            if(!exists()){
                properties.setProperty("path","D:\\server")
            } else {
                properties.load(FileReader(this))
            }
        }
        path = File(properties.getProperty("path"))
        path.mkdirs()
        lib = MinecraftServerLib(this)
    }


    fun createServer(){

    }

}