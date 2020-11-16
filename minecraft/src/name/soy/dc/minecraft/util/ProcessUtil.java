package name.soy.dc.minecraft.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;


/**
 * 过程工具
 *
 * @author yuanlu
 */
public class ProcessUtil {
	/**
	 * 迷惑接口<br>
	 * https://blog.csdn.net/weixin_43987631/article/details/90440687
	 *
	 * @author yuanlu
	 */
	private interface Kernel32 extends Library {
		/**
		 * 貌似是自己
		 */
		public static Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

		/**
		 * 获取PID
		 *
		 * @param hProcess 进程
		 * @return PID
		 */
		public long GetProcessId(Long hProcess);
	}

	static {
		load(Platform.class);
		load(Kernel32.class);
	}
	/**
	 * 加载某类<br>
	 * 由ClassLoader加载
	 *
	 * @param c 类
	 */
	public static void load(Class<?> c) {
		if (c != null) c.getTypeName();
	}
	/**
	 * 是否是调试模式
	 */
	public static final boolean DEBUG = true;

	/**
	 * 获取一个过程ID
	 *
	 * @param process 过程(进程)
	 * @return PID
	 */
	public static String getProcessId(Process process) {
		long pid = -1;
		Field field = null;
		if (Platform.isWindows()) {
			try {
				field = process.getClass().getDeclaredField("handle");
				field.setAccessible(true);
				pid = Kernel32.INSTANCE.GetProcessId((Long) field.get(process));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (Platform.isLinux() || Platform.isAIX()) {
			try {
				Class<?> clazz = Class.forName("java.lang.UNIXProcess");
				field = clazz.getDeclaredField("pid");
				field.setAccessible(true);
				pid = (Integer) field.get(process);
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		return String.valueOf(pid);
	}

	/**
	 * 杀死进程
	 *
	 * @param process 进程
	 * @return 是否成功杀死
	 */
	public static boolean killProcess(Process process) {
		String PID = getProcessId(process);
		return killProcessByPid(PID);
	}

	/**
	 * 关闭Linux进程
	 *
	 * @param pid 进程的PID
	 * @return 是否成功杀死
	 */
	public static boolean killProcessByPid(String pid) {
		if (pid.isEmpty() || "-1".equals(pid)) return false;// throw new RuntimeException("pid ==" + pid);
		Process process = null;
		BufferedReader reader = null;
		String command = "";
		boolean result = false;
		if (Platform.isWindows()) {
			command = "cmd.exe /c taskkill /PID " + pid + " /F /T ";
		} else if (Platform.isLinux() || Platform.isAIX()) {
			command = "kill -9 " + pid;
		}
		try {
			// 杀掉进程
			process = Runtime.getRuntime().exec(command);
			reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (DEBUG) System.out.printf("[PROCESS] kill %s: %s", pid, line);
			}
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		} finally {
			if (process != null) process.destroy();

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}
}