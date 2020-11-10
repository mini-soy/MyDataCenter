package name.soy.dc.log

import name.soy.dc.DataCenter
import java.util.function.Predicate
import java.util.logging.Level
import java.util.logging.LogRecord

class LogManager(private val center: DataCenter) {
	
	/**
	 * 查询从开始时间到现在的所有日志
	 */
	fun queryLog(start: Long): List<LogData> = queryLog(start, System.currentTimeMillis())
	
	/**
	 * 查询从开始时间到结束时间的所有日志
	 */
	fun queryLog(start: Long, end: Long): List<LogData> = queryLog(start, end) { true }
	
	fun queryLog(start: Long, predicate: Predicate<LogEntry>): List<LogData> = queryLog(start, System.currentTimeMillis(), predicate)
	
	/**
	 * 查询从开始时间到结束时间的,所限制类型的数据
	 */
	fun queryLog(start: Long, end: Long, predicate: Predicate<LogEntry>): List<LogData> = queryLog(start, end, predicate, { true })
	fun queryLog(start: Long, end: Long, entrypre: Predicate<LogEntry>, levelpre: Predicate<Level>) = queryLog(start, end, entrypre, levelpre, { true })
	fun queryLog(start: Long, end: Long, entrypre: Predicate<LogEntry>, levelpre: Predicate<Level>, msgpre: Predicate<String>): List<LogData> = queryLog({ time: Long ->
		time in start..end
	}, entrypre, levelpre, msgpre)
	
	fun queryLog(timepre: Predicate<Long>, entrypre: Predicate<LogEntry>, levelpre: Predicate<Level>, msgpre: Predicate<String>): List<LogData> {
		TODO("完成查询")
	}
	
	fun log(entry: LogEntry, level: Level, msg: String) = log(entry, LogRecord(level, msg))
	
	fun log(entry: LogEntry, rec: LogRecord) =
			Thread({
				center.sql().execute("Insert Into log values(from_unixtime(${rec.millis / 1000}),'${entry.name}','${rec.level.name}','${rec.message}')")
			}, "log-" + rec.millis).start()
	
	data class LogData(val entry: LogEntry, val time: Long, val level: Level, val msg: String)
	
}

