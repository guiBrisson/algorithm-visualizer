package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer

class InsertionSort: Sort() {
    override val algorithmName: String = "Insertion sort"
    override val chartTracer: ChartTracer = ChartTracer()

    override suspend fun sort(
        arr: IntArray,
        onFinish: () -> Unit
    ) {
        chartTracer.initialState(arr.toList())
        for(i in 1 until arr.size) {
            var j = i - 1
            val key = arr[i]
            chartTracer.selectState(i)

            while (j>=0 && key < arr[j]) {
                arr[j+1] = arr[j]
                chartTracer.swapState(arr.toList(), j + 1, j)
                j--
            }

            arr[j+1] = key
            chartTracer.swapState(arr.toList(), j + 1, i)
        }

        chartTracer.finalState(arr.toList())
        onFinish()
    }
}
