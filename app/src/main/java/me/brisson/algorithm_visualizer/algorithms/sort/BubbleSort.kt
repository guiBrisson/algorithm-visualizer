package me.brisson.algorithm_visualizer.algorithms.sort


class BubbleSort : ISort {
    override val algorithmName: String = "Bubble sort"

    override suspend fun sort(arr: IntArray, onSwap: (IntArray) -> Unit, onFinish: () -> Unit) {
        for (i in arr.size - 1 downTo 1) {
            for (j in 0 until i) {
                if (arr[j] > arr[j + 1]) {

                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = temp
                    onSwap(arr)
                }
            }
        }
        onFinish()
    }

}