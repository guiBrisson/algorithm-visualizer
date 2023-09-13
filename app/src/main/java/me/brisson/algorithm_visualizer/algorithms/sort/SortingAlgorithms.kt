package me.brisson.algorithm_visualizer.algorithms.sort

enum class SortingAlgorithms(val algorithmClass: ISort) {
    INSERTION(algorithmClass = InsertionSort()),
    BUBBLE(algorithmClass = BubbleSort())
}
