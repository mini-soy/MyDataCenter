package name.soy.dc.sql

import name.soy.dc.DataCenter
import name.soy.dc.annotation.Manager
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

/**
 * 未使用conn2,暂不进行操作
 */
@Manager("sql")
class SQLSystem(center: DataCenter) {
	private var conn: Connection? = null
	private var conn2: Connection? = null
	fun execute(sql: String): Boolean {
		try {
			return conn!!.createStatement().execute(sql)
		} catch (throwables: SQLException) {
			throwables.printStackTrace()
		}
		return false
	}

	fun query(sql: String): ResultSet? {
		try {
			return conn!!.createStatement().executeQuery(sql)
		} catch (throwables: SQLException) {
			throwables.printStackTrace()
		}
		return null
	}

	init {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver")
		} catch (e: ClassNotFoundException) {
			e.printStackTrace()
		}
		try {
			conn = DriverManager.getConnection(String.format(
					"jdbc:mysql://%s/%s?autoReconnect=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF8",
					center.privateData.SQLAddress(), center.privateData.SQLDatabase()),
					center.privateData.SQLUser(),
					center.privateData.SQLPwd())
		} catch (e: SQLException) {
			e.printStackTrace()
		}
		try {
			conn2 = DriverManager.getConnection(String.format(
					"jdbc:mysql://%s/%s?autoReconnect=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF8",
					center.privateData.SQL2Address(),center.privateData.SQL2Database()))
        } catch (e:SQLException) {
			e.printStackTrace()
        }
	}
}