package me.brisson.algorithm_visualizer.ui.screens.sorting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.algorithm_visualizer.ui.components.AlgorithmController
import me.brisson.algorithm_visualizer.ui.components.SortAlgorithmVisualizer
import me.brisson.algorithm_visualizer.ui.components.ConsoleLog
import me.brisson.algorithm_visualizer.ui.components.ConsoleLogState
import me.brisson.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme
import me.brisson.algorithm_visualizer.ui.theme.background_dark
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SortingRoute(
    modifier: Modifier = Modifier,
    viewModel: SortingViewModel = viewModel(),
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.runSortingToPopulateArrayLevels()
    }

    SortingScreen(
        modifier = modifier,
        uiState = uiState,
        onUpdateConsoleLogState = { viewModel.onEvent(SortingEvents.UpdateConsoleLogState(it)) },
        onPlayPause = { viewModel.onEvent(SortingEvents.PlayPause) },
        onPrevious = { viewModel.onEvent(SortingEvents.Previous) },
        onNext = { viewModel.onEvent(SortingEvents.Next) },
        onSpeed = { viewModel.onEvent(SortingEvents.ChangeSpeed(it)) },
        onBack = onBack,
    )
}

@Composable
internal fun SortingScreen(
    modifier: Modifier,
    uiState: SortingUIState,
    onUpdateConsoleLogState: (ConsoleLogState) -> Unit,
    onPlayPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onSpeed: (Float) -> Unit,
    onBack: () -> Unit,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopBar(
            sortAlgorithmName = uiState.algorithmName,
            onBack = onBack,
            onInfo = { },
            onMore = { },
        )

        uiState.chartState?.let { chartState ->
            SortAlgorithmVisualizer(
                modifier = Modifier.weight(1f),
                chartState = chartState,
            )
        }

        ConsoleLog(
            state = uiState.consoleLogState,
            onToggleState = onUpdateConsoleLogState,
            logMessages = uiState.logMessages,
        )

        AlgorithmController(
            isAvailable = uiState.isVisualizerAvailable,
            isPlaying = uiState.isPlaying,
            hasFinished = uiState.isSortingFinished,
            onPlayPause = onPlayPause,
            onPrevious = onPrevious,
            onNext = onNext,
            onSpeed = onSpeed,
        )
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    sortAlgorithmName: String,
    onBack: () -> Unit,
    onInfo: () -> Unit,
    onMore: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(background_dark)
            .padding(horizontal = 14.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(modifier = Modifier.offset(x = (-6).dp), onClick = onBack) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp)
                .weight(1f),
            text = sortAlgorithmName,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Medium,
            ),
        )
        IconButton(onClick = onInfo) {
            Icon(
                imageVector = Icons.Rounded.Info,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
        IconButton(onClick = onMore) {
            Icon(
                imageVector = Icons.Rounded.MoreVert,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSortingScreen() {
    AlgorithmVisualizerTheme {
        val uiState = SortingUIState(
            algorithmName = "Bubble sort"
        )

        SortingScreen(
            modifier = Modifier,
            uiState = uiState,
            onUpdateConsoleLogState = { },
            onPlayPause = { },
            onPrevious = { },
            onNext = { },
            onSpeed = { },
            onBack = { },
        )
    }
}
