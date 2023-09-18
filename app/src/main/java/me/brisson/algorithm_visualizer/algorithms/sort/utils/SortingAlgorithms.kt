package me.brisson.algorithm_visualizer.algorithms.sort.utils

import me.brisson.algorithm_visualizer.R
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.BubbleSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.CombSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.InsertionSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.QuickSort
import me.brisson.algorithm_visualizer.algorithms.sort.implementations.SelectionSort

enum class SortingAlgorithms(val algorithmClass: Sort, val mdResId: Int? = null) {
    INSERTION(algorithmClass = InsertionSort(), mdResId = R.raw.insertion_info),
    BUBBLE(algorithmClass = BubbleSort(), mdResId = R.raw.bubble_info),
    SELECTION(algorithmClass = SelectionSort(), mdResId = R.raw.selection_info),
    QUICK(algorithmClass = QuickSort(), mdResId = R.raw.quick_info),
    COMB(algorithmClass = CombSort(), mdResId = R.raw.comb_info),
}
