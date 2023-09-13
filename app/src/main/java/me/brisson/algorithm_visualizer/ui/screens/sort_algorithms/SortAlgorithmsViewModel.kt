package me.brisson.algorithm_visualizer.ui.screens.sort_algorithms

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.brisson.algorithm_visualizer.algorithms.sort.utils.SortingAlgorithms
import javax.inject.Inject

@HiltViewModel
class SortAlgorithmsViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(SortAlgorithmsUiState())
    val uiState: StateFlow<SortAlgorithmsUiState> = _uiState.asStateFlow()

    fun updateSortAlgorithmsList() {
        val algorithms = enumValues<SortingAlgorithms>().toList()

        _uiState.update {
            it.copy(sortingList = algorithms)
        }
    }
}
