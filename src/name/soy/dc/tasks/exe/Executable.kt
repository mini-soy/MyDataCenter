package name.soy.dc.tasks.exe

import name.soy.dc.tasks.Aligns
import org.jetbrains.annotations.Nullable
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

    open abstract class ExecuteProgress(open val exe: Executable) {
        //进度
        protected var progress = 0
            protected set(value) {
                var old = getStatData()
                field = value
                statChangeCallback.forEach { it.accept(ExecuteProgressCallback(old, getStatData(), this)) }
            }

        //现在执行的显示文字
        protected var progressText: String = ""
            protected set(value) {
                var old = getStatData()
                field = value
                statChangeCallback.forEach { it.accept(ExecuteProgressCallback(old, getStatData(), this)) }
            }

        //执行的UID
        val uid: UUID = UUID.randomUUID()

        //执行状态
        protected var stat: ProgressStat = ProgressStat.PREPARE
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
        protected val dataset: HashMap<String, Any> = HashMap()

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
        var monitor: Thread? = null

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
            p().forEach {
                if (!dataset.containsKey(it.key) && it.value.isNeeded) {
                    return@canRun false
                } else {
                    if (it.value.isList)
                        flag = dataset[it.key] is List<*>
                    else when (it.value.typename) {
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
            return true
        }


        protected abstract fun run(): Int
    }
}