package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.ISort

class InsertionSort: ISort {
    override val algorithmName: String = "Insertion sort"

    override suspend fun sort(
        arr: IntArray,
        onSwap: (IntArray) -> Unit,
        onFinish: () -> Unit,
    ) {
        for(i in 1 until arr.size) {
            var j = i - 1
            val key = arr[i]

            while (j>=0 && key < arr[j]) {
                arr[j+1] = arr[j]
                onSwap(arr)
                j--
            }

            arr[j+1] = key
            onSwap(arr)
        }

        onFinish()
    }
}