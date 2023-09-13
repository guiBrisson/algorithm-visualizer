package me.brisson.algorithm_visualizer.algorithms.sort.utils

interface ISort {
    val algorithmName: String
    suspend fun sort(arr: IntArray, onSwap: (IntArray) -> Unit, onFinish: () -> Unit)
}
