package me.brisson.algorithm_visualizer.algorithms.utils

data class ChartState(
    val arr: List<Int>,
    val indicesIndicators: Pair<List<Int>, ChartIndicatorType>? = null,
)

enum class ChartIndicatorType {
    SELECT, SWAP
}

class ChartTracer {
    private var _chartStateList = mutableListOf<ChartState>()
    private val _logTracer = LogTracer()

    fun initialState(arr: List<Int>) {
        _chartStateList.add(ChartState(arr))
        _logTracer.logInitial(arr)
    }

    fun selectState(vararg indices: Int) {
        val arr = _chartStateList.last().arr
        val indicesIndicators = Pair(indices.toList(), ChartIndicatorType.SELECT)

        _chartStateList.add(ChartState(arr, indicesIndicators))
    }

    /**
     * Swap values positions
     *
     * @param x first value index
     * @param y second value index
     *
     */
    fun swapState(arr: List<Int>, x: Int, y: Int) {
        val indicesIndicators = Pair(listOf(x, y), ChartIndicatorType.SWAP)

        _chartStateList.add(ChartState(arr, indicesIndicators))
        _logTracer.logSwap(_chartStateList.lastIndex, arr[x], arr[y])
    }

    fun finalState(arr: List<Int>) {
        _chartStateList.add(ChartState(arr))
        _logTracer.logFinish(_chartStateList.lastIndex, arr)
    }

    fun logMessages() = _logTracer.logMessages()

    fun chartStateList() = _chartStateList.toList()
}
