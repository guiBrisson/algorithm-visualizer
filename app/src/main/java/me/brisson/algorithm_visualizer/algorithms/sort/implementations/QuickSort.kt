package me.brisson.algorithm_visualizer.algorithms.sort.implementations

import me.brisson.algorithm_visualizer.algorithms.sort.utils.ISort

class QuickSort: ISort {
    override val algorithmName: String = "Quick sort"

    override suspend fun sort(
        arr: IntArray,
        onStep: (IntArray, IntArray) -> Unit,
        onFinish: () -> Unit
    ) {
        quickSort(arr, 0, arr.size - 1, onStep)
        onFinish()
    }

    private fun quickSort(
        arr: IntArray,
        left: Int,
        right: Int,
        onStep: (IntArray, IntArray) -> Unit,
    ) {
        var left1 = left
        var l: Int
        var r: Int
        var s: Int
        while (right > left1) {
            l = left1
            r = right
            s = arr[left1]
            while (l < r) {
                onStep(arr, intArrayOf(l, r))
                while (arr[r] > s) {
                    r--
                    onStep(arr, intArrayOf(l, r))
                }
                arr[l] = arr[r]
                onStep(arr, intArrayOf(l, r))
                while (s >= arr[l] && l < r) {
                    l++
                    onStep(arr, intArrayOf(l, r))
                }
                arr[r] = arr[l]
                onStep(arr, intArrayOf(l, r))
            }
            arr[l] = s
            onStep(arr, intArrayOf(l, r))
            quickSort(arr, left1, l - 1, onStep)
            left1 = l + 1
        }
    }

}
