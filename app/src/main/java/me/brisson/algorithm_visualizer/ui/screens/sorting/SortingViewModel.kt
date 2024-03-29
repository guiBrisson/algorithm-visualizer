package me.brisson.algorithm_visualizer.ui.screens.sorting

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.algorithm_visualizer.algorithms.sort.utils.Sort
import me.brisson.algorithm_visualizer.algorithms.sort.utils.SortingAlgorithms
import me.brisson.algorithm_visualizer.algorithms.utils.ChartState
import me.brisson.algorithm_visualizer.algorithms.utils.MessageLog
import me.brisson.algorithm_visualizer.navigation.AppNavigationArgs
import me.brisson.algorithm_visualizer.ui.components.ConsoleLogState
import kotlin.random.Random

class SortingViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _sortingClassName: String =
        checkNotNull(savedStateHandle[AppNavigationArgs.SORT_ALGORITHM_CLASS_NAME])

    private var sortClass: Sort? = Sort.instantiateClass(_sortingClassName)

    private val _uiState = MutableStateFlow(SortingUIState())
    val uiState: StateFlow<SortingUIState> = _uiState.asStateFlow()

    private var delay = BASE_SPEED

    private var chartStateList: List<ChartState> = emptyList()
    private var messageLogList: List<MessageLog> = emptyList()
    private var _sortingStateIndex by mutableIntStateOf(0)


    fun runSortingToPopulateArrayLevels() = viewModelScope.launch(Dispatchers.IO) {
        sortClass?.let { clazz ->
            clazz.sort(
                arr = IntArray(20) { Random.nextInt(from = 10, until = 300) },
                onFinish = {
                    chartStateList = clazz.chartTracer.chartStateList()
                    messageLogList = clazz.chartTracer.logMessages()

                    _uiState.update {
                        it.copy(
                            isVisualizerAvailable = true,
                            consoleLogState = ConsoleLogState.EXPANDED,
                            algorithmName = clazz.algorithmName,
                            chartState = chartStateList.first(),
                            logMessages = logMessagesBasedOnIndex(0)
                        )
                    }
                }
            )

        }
    }

    fun updateInfoButtonAvailability() {
        val enumAlgorithm = enumValues<SortingAlgorithms>().find {
            it.algorithmClass.algorithmName == sortClass?.algorithmName
        }

        _uiState.update { it.copy(infoMdResId = enumAlgorithm?.mdResId) }
    }

    fun resetAllValues() {
        sortClass = Sort.instantiateClass(_sortingClassName)
        chartStateList = emptyList()
        messageLogList = emptyList()

        _uiState.value = SortingUIState()
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
        Log.d(TAG, "Algorithm speed: $delay long ($speed x)")
    }

    private fun updateConsoleLogState(state: ConsoleLogState) {
        _uiState.update { it.copy(consoleLogState = state) }
    }

    private fun next() {
        if (_sortingStateIndex < chartStateList.lastIndex) {
            _sortingStateIndex++
            _uiState.update {
                it.copy(
                    isPlaying = false,
                    chartState = chartStateList[_sortingStateIndex],
                    logMessages = logMessagesBasedOnIndex(_sortingStateIndex),
                )
            }

            if (_sortingStateIndex == chartStateList.lastIndex) {
                updateUIAsFinished()
            }
        }
    }

    private fun previous() {
        if (_sortingStateIndex > 0) {
            _sortingStateIndex--
            _uiState.update {
                it.copy(
                    isPlaying = false,
                    isSortingFinished = false,
                    chartState = chartStateList[_sortingStateIndex],
                    logMessages = logMessagesBasedOnIndex(_sortingStateIndex),
                )
            }
        }
    }

    private fun playPause() {
        if (_uiState.value.isSortingFinished) {
            replay()
            return
        }
        _uiState.update { it.copy(isPlaying = !_uiState.value.isPlaying) }
        runThroughSortedArrayLevels()
    }

    private fun replay() {
        _sortingStateIndex = 0
        clearLogMessages()

        _uiState.update {
            it.copy(
                isSortingFinished = false,
                chartState = chartStateList[_sortingStateIndex],
                logMessages = logMessagesBasedOnIndex(_sortingStateIndex),
                isPlaying = false
            )
        }
    }

    private fun clearLogMessages() {
        _uiState.update { it.copy(logMessages = emptyList()) }
    }

    private fun runThroughSortedArrayLevels() = viewModelScope.launch(Dispatchers.IO) {
        for (i in _sortingStateIndex until chartStateList.size) {
            _sortingStateIndex = i

            if (_uiState.value.isPlaying) {
                val currentLogMessageList = logMessagesBasedOnIndex(i)
                _uiState.update {
                    it.copy(
                        chartState = chartStateList[i],
                        logMessages = currentLogMessageList.toList()
                    )
                }

                delay(delay)
            } else {
                return@launch
            }
        }

        updateUIAsFinished()
    }

    private fun logMessagesBasedOnIndex(i: Int): MutableList<MessageLog> {
        val currentLogMessageList = _uiState.value.logMessages.toMutableList()

        currentLogMessageList.removeIf { it.index >= i }

        if (messageLogList.map { it.index }.contains(i)) {

            messageLogList.find { it.index == i }?.let {
                currentLogMessageList.add(it)
            }
        }
        return currentLogMessageList
    }

    private fun updateUIAsFinished() {
        _uiState.update { it.copy(isSortingFinished = true) }
    }

    companion object {
        private val TAG = this::class.java.simpleName
        private const val BASE_SPEED = 200L
    }
}
