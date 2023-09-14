package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer

class SelectionSort : Sort() {
    override val algorithmName: String = "Selection sort"
    override val chartTracer: ChartTracer = ChartTracer()

    override suspend fun sort(
        arr: IntArray,
        onFinish: () -> Unit
    ) {
        chartTracer.initialState(arr.toList())
        var minIndex: Int

        for (i in arr.indices) {
            minIndex = i
            for (j in i + 1 until arr.size) {
                chartTracer.selectState(j)
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }
            val temp: Int = arr[minIndex]
            arr[minIndex] = arr[i]
            arr[i] = temp
            chartTracer.swapState(arr.toList(), minIndex, i)
        }

        chartTracer.finalState(arr.toList())
        onFinish()
    }
}
