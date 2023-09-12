package me.brisson.algorithm_visualizer.algorithms.sort

interface ISort {

    suspend fun sort(arr: IntArray, onSwap: (IntArray) -> Unit, onFinish: () -> Unit)

}
