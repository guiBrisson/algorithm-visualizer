package me.brisson.algorithm_visualizer.algorithms.sort.utils

import me.brisson.algorithm_visualizer.algorithms.sort.implementations.BubbleSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.CombSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.InsertionSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.QuickSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.SelectionSort

enum class SortingAlgorithms(val algorithmClass: Sort) {
    INSERTION(algorithmClass = InsertionSort()),
    BUBBLE(algorithmClass = BubbleSort()),
    SELECTION(algorithmClass = SelectionSort()),
    QUICK(algorithmClass = QuickSort()),
    COMB(algorithmClass = CombSort()),
}
