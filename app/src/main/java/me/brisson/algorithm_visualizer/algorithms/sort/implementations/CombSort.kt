package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer
import kotlin.math.floor

class CombSort : Sort() {
    override val algorithmName: String = "Comb sort"
    override val chartTracer: ChartTracer = ChartTracer()

    override suspend fun sort(
        arr: IntArray,
        onFinish: () -> Unit
    ) {
        val length: Int = arr.size
        var gap = length
        var swapped: Boolean
        val shrink = 1.3f

        chartTracer.initialState(arr.toList())
        do {
            swapped = false
            gap = floor((gap / shrink).toDouble()).toInt()
            if (gap < 1) {
                gap = 1
            }

            for (i in 0 until length - gap) {
                chartTracer.selectState(i, i + gap)
                if (arr[i] > arr[i + gap]) {
                    swap(i, i + gap, arr)
                    swapped = true
                }
            }

        } while (gap != 1 || swapped)

        chartTracer.finalState(arr.toList())
        onFinish()
    }

    private fun swap(x: Int, y: Int, array: IntArray) {
        val temp = array[x]
        array[x] = array[y]
        array[y] = temp
        chartTracer.swapState(array.toList(), x, y)
    }

}
