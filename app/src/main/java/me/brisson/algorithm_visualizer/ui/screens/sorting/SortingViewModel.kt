package me.brisson.algorithm_visualizer.ui.screens.sorting

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
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
import me.brisson.algorithm_visualizer.algorithms.sort.utils.ISort
import me.brisson.algorithm_visualizer.navigation.AppNavigationArgs
import me.brisson.algorithm_visualizer.ui.components.ConsoleLogState
import javax.inject.Inject

@HiltViewModel
class SortingViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _sortingClassName: String =
        checkNotNull(savedStateHandle[AppNavigationArgs.SORT_ALGORITHM_ID])

    private val _uiState = MutableStateFlow(SortingUIState())
    val uiState: StateFlow<SortingUIState> = _uiState.asStateFlow()

    private val sortClass: ISort? = instantiateClass(_sortingClassName)

    private var delay = BASE_SPEED

    private var sortedArrayLevels = mutableListOf<List<Int>>()
    private var indicesPosition = mutableListOf<List<Int>>()
    private var sortingStateIndex by mutableIntStateOf(0)

    private fun instantiateClass(className: String): ISort? {
        return try {
            val clazz = Class.forName(className)
            val instance = clazz.getDeclaredConstructor().newInstance()
            Log.d(TAG, "instantiateClass: ${instance::class.java.simpleName}")

            if (instance is ISort) {
                instance
            } else null
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
            null
        } catch (e: InstantiationException) {
            e.printStackTrace()
            null
        }
    }

    fun runSortingToPopulateArrayLevels() {
        viewModelScope.launch(Dispatchers.IO) {
            sortClass?.let { clazz ->
                clazz.sort(
                    arr = _uiState.value.arr.clone(),
                    onStep = { levels, positions ->
                        sortedArrayLevels.add(levels.toMutableList())
                        indicesPosition.add(positions.toMutableList())
                    },
                    onFinish = {
                        _uiState.update {
                            it.copy(
                                isVisualizerAvailable = true,
                                algorithmName = clazz.algorithmName
                            )
                        }
                    }
                )
            }
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
            sortingStateIndex++
            _uiState.update {
                it.copy(
                    arr = sortedArrayLevels[sortingStateIndex].toIntArray(),
                    indicesPositions = indicesPosition[sortingStateIndex].toIntArray(),
                    isPlaying = false,
                )
            }

            if (sortingStateIndex == sortedArrayLevels.size - 1) {
                updateUiAsFinished()
            }
        }
    }

    private fun previous() {
        if (sortingStateIndex > 0) {
            sortingStateIndex--
            _uiState.update {
                it.copy(
                    arr = sortedArrayLevels[sortingStateIndex].toIntArray(),
                    indicesPositions = indicesPosition[sortingStateIndex].toIntArray(),
                    isPlaying = false,
                    isSortingFinished = false,
                )
            }
        }
    }

    private fun playPause() {
        if (_uiState.value.isSortingFinished) {
            replay()
        }
        _uiState.update { it.copy(isPlaying = !_uiState.value.isPlaying) }
        runThroughSortedArrayLevels()
    }

    private fun replay() {
        sortingStateIndex = 0
        _uiState.update {
            it.copy(
                isSortingFinished = false,
                arr = sortedArrayLevels[sortingStateIndex].toIntArray(),
                indicesPositions = null,
            )
        }
    }

    private fun runThroughSortedArrayLevels() = viewModelScope.launch(Dispatchers.IO) {
        for (i in sortingStateIndex until sortedArrayLevels.size) {
            sortingStateIndex = i

            if (_uiState.value.isPlaying) {
                delay(delay)
                _uiState.update {
                    it.copy(
                        arr = sortedArrayLevels[i].toIntArray(),
                        indicesPositions = indicesPosition[i].toIntArray(),
                    )
                }
            } else {
                return@launch
            }
        }

        updateUiAsFinished()
    }

    private fun updateUiAsFinished() {
        _uiState.update { it.copy(isSortingFinished = true, indicesPositions = null) }
    }

    companion object {
        private val TAG = SortingViewModel::class.java.simpleName
        private const val BASE_SPEED = 200L
    }
}
