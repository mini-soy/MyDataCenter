package name.soy.dc.device.aida

import com.google.gson.Gson
import com.google.gson.JsonObject
import name.soy.dc.device.Device
import name.soy.dc.device.aida.AIDAEntrySimple.*
import java.util.*
import kotlin.collections.HashMap


/**
 * 设备的数据集合地，用于
 */
class DeviceData(device: Device) {
    companion object{
        val gson = Gson()
        init {
            
        }
    }
    //AIDA数据
    var aidaData = HashMap<AIDAEntry<*>, String>()
    //AIDA存储
    var aidaset = HashMap<String, AIDAEntry<*>>()

    /**
     * NIC数据，初始化详见{@code initNIC()}
     */
    var nicdata = LinkedList<NICData>()
    //磁盘名称列表
    var disknames = LinkedList<String>()

    //设备
    var device = device
    private inline fun addaida(s: AIDAEntrySimple, data: Any?) {
        aidaset["Sample${aidaset.size + 1}"] = AIDAEntry(s, data);
    }

    /**
     * AIDA模块
     */
    init {
        addaida(RUNNING_TIME, null)
        addaida(CPU_FREQUENCY, null)
        addaida(CPU_USAGE, null)
        for(i in 1..64)
            addaida(CPU_UNIT_USAGE, i)
        addaida(MEMORY_USAGE, null)
        addaida(MEMORY_UNUSED, null)

        addaida(VMEMORY_USAGE, null)
        addaida(VMEMORY_UNUSED, null)
        for(i in 1..16) {
            addaida(DISKIO_USAGE, i)
            addaida(DISKI_SPEED, i)
            addaida(DISKO_SPEED, i)
        }
        for(i in 'A'..'Z') {
            addaida(DRIVER_USAGE, "$i")
            addaida(DRIVER_UNUSED, "$i")
        }
        addaida(EXTERNAL_IP, null)
        for(i in 1..8) {
            addaida(NIC_DSPEED, i)
            addaida(NIC_USPEED, i)
            addaida(NIC_DOWNLOAD, i)
            addaida(NIC_UPLOAD, i)
            addaida(NIC_MAXSPEED, i)
        }
        for(i in 1..16)
            addaida(DISK_TEMPERATURE, i)
        addaida(GPU_USAGE, null)
        addaida(GRAM_USAGE, null)
        addaida(GRAM_UNUSED, null)
        addaida(GPU_WASTES, null)

        addaida(CPU_TEMPERATURE, null)
        addaida(GPU_TEMPERATURE, null)

        addaida(MB_TEMPERATURE, null)
        addaida(CPU_WASTES, null)

        addaida(POWER_STAT, null)
        addaida(POWER_LEVEL, null)

        addaida(GT_WASTES, null)
        addaida(GT_TEMPERATURE, null)

        addaida(SYSTEM_PROCESSING, null)
    }

    fun pushAIDA(value: HashMap<String, String>) {
        value.forEach {
            aidaset[it.key]?.let { it1 -> aidaData[it1] = it.value };
        }

        inflate()
    }

    private fun inflate() {
        aidaData.forEach{
            var initnic = false
            var initdisk = false
            var simple = it.key;
            if(simple.simple == DISKIO_USAGE){
                var i = simple.data;
                if(i is Int&&i>disknames.size){
                    initdisk = true;
                }
            }
            if(simple.simple == NIC_MAXSPEED){
                var i = simple.data;
                if(i is Int&&i>disknames.size){
                    initnic = true;
                }
            }
            if(initnic){
                initNIC()
            }
            if(initdisk){
                initdisk();
            }
        }

    }

    /**
     * NIC模块(网卡列表)
     */

    init {
        initNIC()
    }

    private fun initNIC(){
        device.sendcmd("ipconfig /all") {
            val lines: ArrayList<String> = ArrayList(it.readData.split("\n"))
            //移除头信息
            for (i in 0..8)
                lines.removeAt(0)

            var nextv = 0
            val datas = LinkedList<NICData>()
            datas.addFirst(NICData())
            for (i in lines.indices) {
                val s = lines[i].replace("\r", "")
                if (s.trim().isEmpty()) {
                    nextv++
                    if (nextv == 2) {
                        datas.addFirst(NICData())
                        nextv = 0
                    }
                    continue
                }
                val vals = s.split(":".toRegex(), 2)
                if (vals.size == 2) {
                    val data = datas.first
                    val prefix = vals[0].replace(".", "").trim();
                    if (prefix == "描述")
                        data.name = vals[1].trim()
                    else if (prefix == "IPv4 地址")
                        data.ip = vals[1].trim().replace("(首选)", "")
                    else if (prefix == "默认网关")
                        data.gateway = vals[1].trim()
                }
            }
            datas.sortWith { c, c2 -> c.name.compareTo(c2.name) }
            nicdata = datas
        }
    }
    class NICData {
        var name:String= ""
        var ip:String= ""
        var gateway:String=""
    }

    /**
     *
     */
    init{
        initdisk()
    }
    fun initdisk(){
        device.sendcmd("wmic diskdrive get caption") {
            val array:LinkedList<String> = LinkedList(it.readData.split("\n"))
            for (i in array.indices)
                array[i] = array[i].trim()
            array.removeIf{obj->obj.isEmpty()}
            array.removeAt(0)
            disknames = array;
        }
    }


}