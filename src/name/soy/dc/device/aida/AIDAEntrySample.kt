package name.soy.dc.device.aida

import name.soy.dc.device.aida.AIDAType.*

/*
 * AIDA数据单元
 */
enum class AIDAEntrySample(val dis: String, val unit: String, val modifier: Class<*>, val type: AIDAType) {
	
	RUNNING_TIME("已运行时间", "秒", Void.TYPE, UTIL),
	
	CPU_FREQUENCY("CPU频率", "MHz", Void.TYPE, CPU),
	
	CPU_USAGE("CPU使用率", "%", Void.TYPE, CPU),
	
	MEMORY_USAGE("内存已使用", "MB", Void.TYPE, RAM),
	
	MEMORY_UNUSED("内存可用", "MB", Void.TYPE, RAM),
	
	VMEMORY_USAGE("虚拟内存已使用", "MB", Void.TYPE, RAM),
	
	VMEMORY_UNUSED("虚拟内存可用", "MB", Void.TYPE, RAM),
	
	CPU_UNIT_USAGE("CPU核心%d使用率", "%", Integer.TYPE, CPU),
	
	CPU_TEMPERATURE("CPU温度", "℃", Void.TYPE, CPU),
	
	CPU_WASTES("CPU功耗(package)", "W", Void.TYPE, CPU),
	
	DISKIO_USAGE("磁盘%d利用率", "GB", Integer.TYPE, DISK),
	
	DISKI_SPEED("磁盘%d读取速度", "KB/s", Integer.TYPE, DISK),
	
	DISKO_SPEED("磁盘%d写入速度", "KB/s", Integer.TYPE, DISK),
	
	DISK_TEMPERATURE("磁盘%d温度", "℃", Integer.TYPE, DISK),
	
	DRIVER_USAGE("驱动器%s已使用", "GB", Integer.TYPE, DISK),
	
	DRIVER_UNUSED("驱动器%s可用", "GB", Integer.TYPE, DISK),
	
	EXTERNAL_IP("外部ip", "", Void.TYPE, UTIL),
	
	NIC_MAXSPEED("NIC%d最大速率", "Mbps", Integer.TYPE, NET),
	
	NIC_USPEED("NIC%d上传速度", "KB/s", Integer.TYPE, NET),
	
	NIC_DSPEED("NIC%d下载速度", "KB/s", Integer.TYPE, NET),
	
	NIC_UPLOAD("NIC%d上传总量", "MB", Integer.TYPE, NET),
	
	NIC_DOWNLOAD("NIC%d下载总量", "MB", Integer.TYPE, NET),
	
	GRAM_USAGE("显存已使用", "MB", Void.TYPE, GPU),
	
	GRAM_UNUSED("显存可用", "MB", Void.TYPE, GPU),
	
	GPU_USAGE("显卡使用率", "%", Void.TYPE, GPU),
	
	GPU_TEMPERATURE("显卡温度", "℃", Void.TYPE, GPU),
	
	GPU_WASTES("显卡功耗比", "%", Void.TYPE, GPU),
	
	GT_WASTES("核显功耗", "W", Void.TYPE, GPU),
	
	GT_TEMPERATURE("核显温度", "℃", Void.TYPE, GPU),
	
	MB_TEMPERATURE("主板温度", "℃", Void.TYPE, UTIL),
	
	POWER_STAT("电池状态", "", Void.TYPE, UTIL),
	
	POWER_LEVEL("电量", "%", Void.TYPE, UTIL),
	
	SYSTEM_PROCESSING("进程数量", "", Void.TYPE, UTIL);
	
	
	//哈哈，输出给aida-static.js用的
	companion object {
//		init {
//			var s = "%s:{\n" +
//					"        dis:\"%s\",\n" +
//					"        type:\"%s\",\n" +
//					"        unit:\"%s\"\n" +
//					"    },"
//			for (e in values()){
//				println(s.format(e.name,e.type,e.dis,e.unit))
//			}
//		}
	}
}