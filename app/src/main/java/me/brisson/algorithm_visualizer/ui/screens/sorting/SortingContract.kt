package me.brisson.algorithm_visualizer.ui.screens.sorting

import me.brisson.algorithm_visualizer.algorithms.utils.ChartState
import me.brisson.algorithm_visualizer.algorithms.utils.MessageLog
import me.brisson.algorithm_visualizer.ui.components.ConsoleLogState

data class SortingUIState(
    val algorithmName: String = "",
    val infoMdResId: Int? = null,

    // Visualizer
    val isVisualizerAvailable: Boolean = false,
    val isPlaying: Boolean = false,
    val isSortingFinished: Boolean = false,

    // Chart
    val chartState: ChartState? = null,

    // Console
    val consoleLogState: ConsoleLogState = ConsoleLogState.COLLAPSED,
    val logMessages: List<MessageLog> = emptyList(),
)

sealed interface SortingEvents {
    object PlayPause : SortingEvents
    object Previous : SortingEvents
    object Next : SortingEvents
    data class ChangeSpeed(val speed: Float): SortingEvents

    data class UpdateConsoleLogState(val state: ConsoleLogState): SortingEvents
}
