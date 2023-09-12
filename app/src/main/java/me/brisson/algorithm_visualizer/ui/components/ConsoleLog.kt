package me.brisson.algorithm_visualizer.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import me.brisson.algorithm_visualizer.R


@Composable
fun ConsoleLog(
    modifier: Modifier = Modifier,
    state: ConsoleLogState = ConsoleLogState.COLLAPSED,
    onToggleState: (ConsoleLogState) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    val animatedPadding by animateDpAsState(
        when (state) {
            ConsoleLogState.EXPANDED -> 16.dp
            ConsoleLogState.COLLAPSED -> 4.dp
        },
        label = "",
    )

    val height = when (state) {
        ConsoleLogState.EXPANDED -> 350.dp
        ConsoleLogState.COLLAPSED -> 32.dp
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize()
            .height(height)
            .background(MaterialTheme.colorScheme.background)
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
            .padding(horizontal = 20.dp, vertical = animatedPadding),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_terminal),
            contentDescription = "terminal icon",
            tint = MaterialTheme.colorScheme.onBackground,
        )
    }
}

enum class ConsoleLogState {
    EXPANDED, COLLAPSED
}
