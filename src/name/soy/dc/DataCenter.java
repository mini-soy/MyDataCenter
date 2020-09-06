package name.soy.dc;

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
	public HardwareSystem hardwareSystem;
	
	private DataCenter() {
		
	}
	
}
