package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.ISort

class InsertionSort: ISort {
    override val algorithmName: String = "Insertion sort"

    override suspend fun sort(
        arr: IntArray,
        onStep: (IntArray, Pair<Int, Int>) -> Unit,
        onFinish: () -> Unit
    ) {
        for(i in 1 until arr.size) {
            var j = i - 1
            val key = arr[i]
            onStep(arr, Pair(i, j))

            while (j>=0 && key < arr[j]) {
                arr[j+1] = arr[j]
                onStep(arr, Pair(i, j))
                j--
                onStep(arr, Pair(i, j))
            }

            arr[j+1] = key
            onStep(arr, Pair(i, j))
        }

        onFinish()
    }
}
