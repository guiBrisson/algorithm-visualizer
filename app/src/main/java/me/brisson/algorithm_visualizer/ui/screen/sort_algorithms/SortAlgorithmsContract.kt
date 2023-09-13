package me.brisson.algorithm_visualizer.ui.screen.sort_algorithms

import me.brisson.algorithm_visualizer.algorithms.sort.SortingAlgorithms

data class SortAlgorithmsUiState(
    val sortingList: List<SortingAlgorithms> = emptyList()
)
