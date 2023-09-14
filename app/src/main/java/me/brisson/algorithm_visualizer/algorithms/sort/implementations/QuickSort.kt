package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer


class QuickSort: Sort() {
    override val algorithmName: String = "Quick sort"
    override val chartTracer: ChartTracer = ChartTracer()

    override suspend fun sort(
        arr: IntArray,
        onFinish: () -> Unit
    ) {
        chartTracer.initialState(arr.toList())
        quickSort(arr, 0, arr.lastIndex)

        chartTracer.finalState(arr.toList())
        onFinish()
    }

    private fun quickSort(arr: IntArray, left: Int, right: Int) {
        var left = left
        var l: Int
        var r: Int
        var s: Int
        while (right > left) {
            l = left
            r = right
            s = arr[left]
            while (l < r) {
                chartTracer.selectState(left, right)
                while (arr[r] > s) {
                    chartTracer.selectState(r)
                    r--
                }
                arr[l] = arr[r]
                chartTracer.swapState(arr.toList(), l, arr[r])
                while (s >= arr[l] && l < r) {
                    chartTracer.selectState(l)
                    l++
                }
                arr[r] = arr[l]
                chartTracer.swapState(arr.toList(), r, arr[l])
            }
            arr[l] = s
            chartTracer.swapState(arr.toList(), l, s)
            quickSort(arr, left, l - 1)
            left = l + 1
        }
    }

}
