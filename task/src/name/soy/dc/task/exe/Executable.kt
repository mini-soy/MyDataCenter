package name.soy.dc.task.exe

import name.soy.dc.task.Aligns
import name.soy.dc.task.ExecuteProgressCallback
import name.soy.dc.task.ProgressStat
import java.io.InputStream
import java.util.*
import java.util.function.Consumer
import kotlin.collections.HashMap

/**
 *
 * @author soy
 */
interface Executable {
	fun needParameters(): () -> HashMap<String, Aligns<*>>
	
	fun execute(): ExecuteProgress
	
	fun getName(): String
	
	fun returnParameters(): () -> HashMap<String, Class<*>>
	
	abstract class ExecuteProgress(open val exe: Executable) {
		lateinit var align: HashMap<String, Aligns<*>>
		
		//进度
		var progress = 0
			protected set(value) {
				var old = getStatData()
				field = value
				statChangeCallback.forEach { it.accept(ExecuteProgressCallback(old, getStatData(), this)) }
			}
		
		//现在执行的显示文字
		var progressText: String = ""
			protected set(value) {
				var old = getStatData()
				field = value
				statChangeCallback.forEach { it.accept(ExecuteProgressCallback(old, getStatData(), this)) }
			}
		
		//执行的UID
		val uid: UUID = UUID.randomUUID()
		
		//执行状态
		var stat: ProgressStat = ProgressStat.PREPARE
			protected set(value) {
				var old = getStatData()
				field = value
				statChangeCallback.forEach { it.accept(ExecuteProgressCallback(old, getStatData(), this)) }
				
			}
		
		private fun getStatData() = ExecuteProgressCallback.Data(progress, progressText, stat)
		
		private val statChangeCallback: MutableList<Consumer<ExecuteProgressCallback>> = ArrayList()
		
		/**
		 * 添加状态改变监听器
		 */
		fun addProgressStatChangeListener(consumer: Consumer<ExecuteProgressCallback>) = statChangeCallback.add(consumer)
		
		/**
		 * 移除状态改变监听器
		 */
		fun removeProgressStatChangeListener(consumer: Consumer<ExecuteProgressCallback>) = statChangeCallback.remove(consumer)
		
		//需要的数据
		protected val dataset: HashMap<String, Any> = object : HashMap<String, Any>() {
			override fun get(key: String): Any? {
				var get = super.get(key)
				if (get == null) {
					get = align[key]?.defaultValue
				}
				return get
			}
		}
		
		//设置数据
		fun setData(s: String, o: Any) {
			dataset[s] = o
			if (canRun()) {
				stat = ProgressStat.READY
			}
		}
		
		fun setData(data: HashMap<String, Any>) {
			dataset.putAll(data)
			if (canRun())
				stat = ProgressStat.READY
		}
		
		//进程是异步的，所以需要线程去守护
		lateinit var monitor: Thread
		
		//运行结束之后的callback
		private val resultCallback: MutableList<Consumer<ExecuteProgress>> = ArrayList()
		
		fun addResultListener(consumer: Consumer<ExecuteProgress>) = resultCallback.add(consumer)
		
		/**
		 * 结果值
		 * p.s:@{code Integer.MIN_VALUE}就是未执行结束
		 */
		var resCode = Integer.MIN_VALUE
		
		val result: HashMap<String, Any> = HashMap()
		
		fun postRun() = (stat == ProgressStat.READY).also {
			monitor = Thread({
				stat = ProgressStat.RUNNING
				resCode = run()
				stat = ProgressStat.FINISH
				resultCallback.forEach { it.accept(this) }
			}, "exe:$uid").apply(Thread::start)
		}
		
		fun canRun(): Boolean {
			val p = exe.needParameters()
			var flag = true
			p().apply {
				forEach {
					if (!dataset.containsKey(it.key) && it.value.needed) {
						return@canRun false
					} else {
						if (it.value.isList)
							flag = dataset[it.key] is List<*>
						else when (it.value.typename) {
							"boolean" -> flag = dataset[it.key] is Boolean
							"int" -> flag = dataset[it.key] is Int
							"double" -> flag = dataset[it.key] is Double
							"file" -> flag = dataset[it.key] is InputStream
							"enum" -> flag = dataset[it.key] is Enum<*>
							"string" -> flag = dataset[it.key] is String
						}
						if (!flag) {
							return false
						}
					}
				}
				align = this
			}
			return true
		}
		
		abstract fun run(): Int
		
		fun waitFor(): Int {
			if (monitor.isAlive)
				monitor.join()
			return resCode;
		}
	}
}