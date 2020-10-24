package name.soy.dc

import name.soy.dc.client.ClientManager
import name.soy.dc.device.DeviceManager
import name.soy.dc.media.MediaCenter
import name.soy.dc.sql.SQLSystem
import name.soy.dc.tasks.ScheduleSystem
import kotlin.system.exitProcess

class DataCenter private constructor() {
	/**
	 * 数据库系统
	 */
	private val sql: SQLSystem = SQLSystem(this)
	fun sql() = sql

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
	var client: ClientManager = ClientManager(this)
	fun client() = client

	/**
	 * 媒体中心
	 */
	private val media: MediaCenter = MediaCenter(this)
	fun media() = media


	var privateData: IPrivateData = MyPrivateData()

	companion object {
		operator fun invoke(): DataCenter {
			return center
		}

		private val center: DataCenter = DataCenter()
	}

	init {
		if(!System.getProperty("os.name").contains("Windows")) {
			error("请等待大神对其他操作系统进行适配")
			exitProcess(1)
        }
		println("DataCenter启动完成")
	}
}