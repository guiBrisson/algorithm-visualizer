package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.ISort

class SelectionSort : ISort {
    override val algorithmName: String = "Selection sort"

    override suspend fun sort(
        arr: IntArray,
        onStep: (IntArray, Pair<Int, Int>) -> Unit,
        onFinish: () -> Unit
    ) {
        var minIndex: Int

        for (i in arr.indices) {
            minIndex = i
            for (j in i + 1 until arr.size) {
                onStep(arr, Pair(i, j))
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }
            val temp: Int = arr[minIndex]
            arr[minIndex] = arr[i]
            onStep(arr, Pair(i, -1))
            arr[i] = temp
            onStep(arr, Pair(i, -1))
        }

        onFinish()
    }
}
