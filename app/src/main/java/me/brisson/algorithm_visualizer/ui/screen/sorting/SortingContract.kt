package me.brisson.algorithm_visualizer.ui.screen.sorting

import me.brisson.algorithm_visualizer.ui.components.ConsoleLogState
import kotlin.random.Random

data class SortingUIState(
    val algorithmName: String = "",
    val isVisualizerAvailable: Boolean = false,
    val isPlaying: Boolean = false,
    val isSortingFinished: Boolean = false,
    val arr: IntArray = IntArray(20) { Random.nextInt(from = 10, until = 300) },
    val consoleLogState: ConsoleLogState = ConsoleLogState.COLLAPSED,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SortingUIState

        if (algorithmName != other.algorithmName) return false
        if (isVisualizerAvailable != other.isVisualizerAvailable) return false
        if (isPlaying != other.isPlaying) return false
        if (isSortingFinished != other.isSortingFinished) return false
        if (!arr.contentEquals(other.arr)) return false
        if (consoleLogState != other.consoleLogState) return false

        return true
    }

    override fun hashCode(): Int {
        var result = algorithmName.hashCode()
        result = 31 * result + isVisualizerAvailable.hashCode()
        result = 31 * result + isPlaying.hashCode()
        result = 31 * result + isSortingFinished.hashCode()
        result = 31 * result + arr.contentHashCode()
        result = 31 * result + consoleLogState.hashCode()
        return result
    }

}

sealed interface SortingEvents {
    object PlayPause : SortingEvents
    object Previous : SortingEvents
    object Next : SortingEvents
    data class ChangeSpeed(val speed: Float): SortingEvents

    data class UpdateConsoleLogState(val state: ConsoleLogState): SortingEvents
}
