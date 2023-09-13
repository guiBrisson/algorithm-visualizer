package me.brisson.algorithm_visualizer.algorithms.sort.utils

import me.brisson.algorithm_visualizer.algorithms.sort.implementations.BubbleSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.InsertionSort

enum class SortingAlgorithms(val algorithmClass: ISort) {
    INSERTION(algorithmClass = InsertionSort()),
    BUBBLE(algorithmClass = BubbleSort())
}
