package name.soy.dc.client.minecraft

import name.soy.dc.client.CenterClient
import name.soy.dc.minecraft.MinecraftServerLib
import name.soy.dc.minecraft.VersionManager
import name.soy.dc.minecraft.handle.IMinecraftServerManager
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class MinecraftServerManager(center: CenterClient): IMinecraftServerManager() {
    var properties: Properties = Properties()
    override lateinit var path: File
    init{
        File("mcsm.properties").apply {
            if(!exists()){
                properties.setProperty("path","D:\\server")
                //TODO:完成服务器配置
                properties.store(FileWriter(this),"MC服务器配置")
            } else {
                properties.load(FileReader(this))
            }
        }
        path = File(properties.getProperty("path"))
        path.mkdirs()
    }
    
    fun createServer(){

    }

}