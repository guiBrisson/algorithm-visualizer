package me.brisson.algorithm_visualizer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.brisson.algorithm_visualizer.R
import me.brisson.algorithm_visualizer.algorithms.utils.ChartLogType
import me.brisson.algorithm_visualizer.algorithms.utils.MessageLog
import me.brisson.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme
import me.brisson.algorithm_visualizer.ui.theme.consoleStyle
import me.brisson.algorithm_visualizer.ui.theme.initialConsoleColor
import me.brisson.algorithm_visualizer.ui.theme.swapColor

enum class ConsoleLogState {
    EXPANDED, COLLAPSED
}

@Composable
fun ConsoleLog(
    modifier: Modifier = Modifier,
    logMessages: List<MessageLog>,
    state: ConsoleLogState = ConsoleLogState.COLLAPSED,
    onToggleState: (ConsoleLogState) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    val newState = if (state == ConsoleLogState.EXPANDED) {
                        ConsoleLogState.COLLAPSED
                    } else {
                        ConsoleLogState.EXPANDED
                    }

                    onToggleState(newState)
                }
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val arrowIconRotation by animateFloatAsState(
                when (state) {
                    ConsoleLogState.EXPANDED -> 0f
                    ConsoleLogState.COLLAPSED -> 180f
                },
                label = ""
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_terminal),
                contentDescription = "terminal icon",
                tint = MaterialTheme.colorScheme.onBackground,
            )

            Icon(
                modifier = Modifier.rotate(arrowIconRotation),
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = "expand/collapse icon",
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        AnimatedVisibility(state == ConsoleLogState.EXPANDED) {
            LogMessages(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp) // Todo: remove fixed height (problem with smaller screens)
                    .padding(),
                logMessages = logMessages,
            )
        }

    }
}

@Composable
internal fun LogMessages(
    modifier: Modifier = Modifier,
    logMessages: List<MessageLog>,
) {
    val scrollState = rememberLazyListState()

    LaunchedEffect(logMessages.size) {
        scrollState.scrollToItem(logMessages.lastIndex)
    }

    LazyColumn(
        modifier = modifier,
        state = scrollState,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        itemsIndexed(logMessages) { index, logMessage ->
            val bottomPaddingDp = if (index == logMessages.lastIndex) 8.dp else 0.dp

            LogMessageItem(
                modifier = Modifier.padding(bottom = bottomPaddingDp),
                logMessage = logMessage,
            )
        }
    }
}

@Composable
internal fun LogMessageItem(
    modifier: Modifier = Modifier,
    logMessage: MessageLog,
) {
    val color: Color = when (logMessage.type) {
        ChartLogType.INITIAL -> initialConsoleColor
        ChartLogType.SWAP -> swapColor
        ChartLogType.FINISH -> MaterialTheme.colorScheme.primary
    }

    Text(
        modifier = modifier,
        text = logMessage.message,
        color = color,
        style = consoleStyle,
    )
}

@Preview
@Composable
private fun PreviewConsoleLog() {
    AlgorithmVisualizerTheme {
        var state by remember { mutableStateOf(ConsoleLogState.EXPANDED) }
        val messages = listOf(
            MessageLog(0, ChartLogType.INITIAL, message = "Initial log"),
            MessageLog(1, ChartLogType.SWAP, message = "Swap log"),
            MessageLog(2, ChartLogType.SWAP, message = "Swap log"),
            MessageLog(3, ChartLogType.FINISH, message = "Finish log"),
        )

        ConsoleLog(
            logMessages = messages,
            state = state,
            onToggleState = { state = it }
        )
    }
}
