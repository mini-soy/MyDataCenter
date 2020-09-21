package name.soy.dc.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import name.soy.dc.DataCenter;

/**
 * 未使用conn2,暂不进行操作
 */
public class SQLSystem {

	private Connection conn, conn2;

	public SQLSystem(DataCenter center) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(String.format(
					"jdbc:mysql://%s/%s?autoReconnect=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF8",
					center.getPrivateData().SQLAddress(),center.getPrivateData().SQLDatabase()),
					center.getPrivateData().SQLUser(),
					center.getPrivateData().SQLPwd());
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		try {
//			conn2 = DriverManager.getConnection(String.format(
//					"jdbc:mysql://%s/%s?autoReconnect=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF8",
//					center.getPrivateData().SQL2Address(),center.getPrivateData().SQL2Database()));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
	}

	public boolean execute(String sql) {
		try {
			return conn.createStatement().execute(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}
	public ResultSet query(String sql) {
		try {
			return conn.createStatement().executeQuery(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
}
