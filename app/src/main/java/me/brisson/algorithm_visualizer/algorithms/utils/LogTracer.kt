package me.brisson.algorithm_visualizer.algorithms.utils

import androidx.compose.runtime.mutableStateListOf

data class MessageLog(
    val index: Int,
    val type: ChartLogType,
    val message: String,
)

enum class ChartLogType {
    INITIAL, SWAP, FINISH
}

class LogTracer {
    private var _logMessages = mutableStateListOf<MessageLog>()

    fun logMessages() = _logMessages.toList()

    private fun log(log: MessageLog) {
        _logMessages.add(log)
    }

    fun logInitial(arr: List<Int>) {
        val message = "initial array = $arr"
        log(MessageLog(0, ChartLogType.INITIAL, message))
    }

    fun logSwap(index: Int, x: Int, y: Int) {
        val message = "swap $x and $y"
        log(MessageLog(index, ChartLogType.SWAP, message))
    }

    fun logFinish(index: Int, arr: List<Int>) {
        val message = "sorted array = $arr"
        log(MessageLog(index, ChartLogType.FINISH, message))
    }
}
