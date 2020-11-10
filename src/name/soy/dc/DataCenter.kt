package name.soy.dc

import name.soy.dc.client.ClientManager
import name.soy.dc.device.DeviceManager
import name.soy.dc.log.LogManager
import name.soy.dc.media.MediaCenter
import name.soy.dc.sql.SQLSystem
import name.soy.dc.tasks.ScheduleSystem
import java.io.File
import java.util.*
import kotlin.system.exitProcess

class DataCenter private constructor() {
	var privateData: IPrivateData = MyPrivateData()
	
	/**
	 * 数据库系统
	 */
	private val sql: SQLSystem = SQLSystem(this)
	fun sql() = sql
	
	/**
	 * 日志管理器
	 */
	private val log: LogManager = LogManager(this)
	fun log() = log
	/**
	 * 任务计划表
	 */
	private val schedule: ScheduleSystem = ScheduleSystem(this)
	fun schedule() = schedule
	/**
	 * 设备管理
	 */
	private val device: DeviceManager = DeviceManager(this)
	fun device() = device

	/**
	 * 客户端管理
	 */
	private val client: ClientManager = ClientManager(this)
	fun client() = client

	/**
	 * 媒体中心
	 */
	private val media: MediaCenter = MediaCenter(this)
	fun media() = media

	companion object {
		private val center: DataCenter = DataCenter()
		
		operator fun invoke(): DataCenter = center
	}
	
	init {
		println("DataCenter启动完成")
	}
}