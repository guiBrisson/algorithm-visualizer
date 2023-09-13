package me.brisson.algorithm_visualizer.ui.screens.sort_algorithms

import me.brisson.algorithm_visualizer.algorithms.sort.utils.SortingAlgorithms

data class SortAlgorithmsUiState(
    val sortingList: List<SortingAlgorithms> = emptyList()
)
