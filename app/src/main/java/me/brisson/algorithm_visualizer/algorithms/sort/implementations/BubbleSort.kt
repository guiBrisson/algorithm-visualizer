package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.ISort


class BubbleSort : ISort {
    override val algorithmName: String = "Bubble sort"

    override suspend fun sort(
        arr: IntArray,
        onStep: (IntArray, IntArray) -> Unit,
        onFinish: () -> Unit
    ) {
        for (i in arr.size - 1 downTo 1) {
            for (j in 0 until i) {
                onStep(arr, intArrayOf(i, j))
                if (arr[j] > arr[j + 1]) {

                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    onStep(arr, intArrayOf(i, j))
                    arr[j+1] = temp
                    onStep(arr, intArrayOf(i, j))
                }
            }
        }
        onFinish()
    }

}