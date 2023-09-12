package me.brisson.algorithm_visualizer.ui.screen.sorting

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.algorithm_visualizer.algorithms.sort.InsertionSort
import me.brisson.algorithm_visualizer.ui.components.ConsoleLogState
import javax.inject.Inject

@HiltViewModel
class SortingViewModel @Inject constructor(
    private val insertionSort: InsertionSort,
) : ViewModel() {
    private val _uiState = MutableStateFlow(SortingUIState())
    val uiState: StateFlow<SortingUIState> = _uiState.asStateFlow()

    private var delay = BASE_SPEED

    private var sortedArrayLevels = mutableListOf<List<Int>>()
    private var sortingStateIndex by mutableIntStateOf(0)

    fun runSortingToPopulateArrayLevels() {
        viewModelScope.launch(Dispatchers.IO) {
            insertionSort.sort(
                _uiState.value.arr.clone(),
                onSwap = { modifiedArray ->
                    sortedArrayLevels.add(modifiedArray.toMutableList())
                },
                onFinish = {
                    _uiState.update { it.copy(isVisualizerAvailable = true) }
                },
            )
        }
    }

    fun onEvent(event: SortingEvents) {
        when (event) {
            SortingEvents.PlayPause -> playPause()
            is SortingEvents.ChangeSpeed -> updateAlgorithmSpeed(event.speed)
            SortingEvents.Previous -> previous()
            SortingEvents.Next -> next()
            is SortingEvents.UpdateConsoleLogState -> updateConsoleLogState(event.state)
        }
    }

    private fun updateAlgorithmSpeed(speed: Float) {
        delay = (BASE_SPEED / speed).toLong()
        Log.d(TAG, "Algorithm speed: $delay long")
    }

    private fun updateConsoleLogState(state: ConsoleLogState) {
        _uiState.update { it.copy(consoleLogState = state) }
    }

    private fun next() {
        if (sortingStateIndex < sortedArrayLevels.size - 1) {
            _uiState.update {
                it.copy(
                    arr = sortedArrayLevels[sortingStateIndex].toIntArray(),
                    isPlaying = false,
                )
            }
            sortingStateIndex++

            if (sortingStateIndex == sortedArrayLevels.size - 1) {
                _uiState.update { it.copy(isSortingFinished = true) }
            }
        }
    }

    private fun previous() {
        if (sortingStateIndex > 0) {
            _uiState.update {
                it.copy(
                    arr = sortedArrayLevels[sortingStateIndex].toIntArray(),
                    isPlaying = false,
                    isSortingFinished = false,
                )
            }
            sortingStateIndex--
        }
    }

    private fun playPause() {
        if (_uiState.value.isSortingFinished) {
            replay()
        }
        runThroughSortedArrayLevels()
        _uiState.update { it.copy(isPlaying = !_uiState.value.isPlaying) }
    }

    private fun replay() {
        sortingStateIndex = 0
        _uiState.update {
            it.copy(
                isSortingFinished = false,
                arr = sortedArrayLevels[sortingStateIndex].toIntArray()
            )
        }
        runThroughSortedArrayLevels()
    }

    private fun runThroughSortedArrayLevels() = viewModelScope.launch(Dispatchers.IO) {
        for (i in sortingStateIndex until sortedArrayLevels.size) {
            sortingStateIndex = i

            if (_uiState.value.isPlaying) {
                delay(delay)
                _uiState.update { it.copy(arr = sortedArrayLevels[i].toIntArray()) }
            } else {
                return@launch
            }
        }

        _uiState.update { it.copy(isSortingFinished = true) }
    }

    companion object {
        private val TAG = SortingViewModel::class.java.simpleName
        private const val BASE_SPEED = 200L
    }
}
