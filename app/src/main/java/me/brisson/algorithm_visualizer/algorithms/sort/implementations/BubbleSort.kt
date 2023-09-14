package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer


class BubbleSort : Sort() {
    override val algorithmName: String = "Bubble sort"
    override val chartTracer: ChartTracer = ChartTracer()

    override suspend fun sort(arr: IntArray, onFinish: () -> Unit) {
        chartTracer.initialState(arr.toList())

        for (i in arr.size - 1 downTo 1) {
            for (j in 0 until i) {
                chartTracer.selectState(j, j + 1)
                if (arr[j] > arr[j + 1]) {
                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = temp
                    chartTracer.swapState(arr.toList(), j, j+1)
                }
            }
        }

        chartTracer.finalState(arr.toList())
        onFinish()
    }

}