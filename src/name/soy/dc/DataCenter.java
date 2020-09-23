package name.soy.dc;

import lombok.Getter;
import name.soy.dc.device.DeviceManager;
import name.soy.dc.sql.SQLSystem;
import name.soy.dc.tasks.ScheduleSystem;

public class DataCenter {
	public static final DataCenter center = new DataCenter();
	/**
	 * -任务计划表
	 */
	public ScheduleSystem schedule;
	/**
	 *
	 */

	public DeviceManager deviceManager;

	@Getter
	IPrivateData privateData = new MyPrivateData();

	SQLSystem sql;
	public SQLSystem sql() {
		return sql;
	}

	private DataCenter() {
		deviceManager = new DeviceManager(this);
		sql = new SQLSystem(this);

		System.out.println("DataCenter启动完成");
	}

}
