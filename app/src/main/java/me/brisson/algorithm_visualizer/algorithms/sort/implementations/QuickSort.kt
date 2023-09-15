package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.utils.ChartTracer


class QuickSort : Sort() {
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

    private fun quickSort(array: IntArray, left: Int, right: Int) {
        val index = partition(array, left, right)
        if (left < index - 1) {
            quickSort(array, left, index - 1)
        }
        if (index < right) {
            quickSort(array, index, right)
        }
    }

    private fun partition(array: IntArray, l: Int, r: Int): Int {
        var left = l
        var right = r
        val pivot = array[(left + right) / 2]
        while (left <= right) {
            chartTracer.selectState(left, right)
            while (array[left] < pivot) left++

            while (array[right] > pivot) right--
            chartTracer.selectState(left, right)

            if (left <= right) {
                swapArray(array, left, right)
                left++
                right--
            }
        }
        return left
    }

    private fun swapArray(arr: IntArray, b: Int, c: Int) {
        val temp = arr[b]
        arr[b] = arr[c]
        arr[c] = temp
        chartTracer.swapState(arr.toList(), b, c)
    }

}
