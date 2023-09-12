package me.brisson.algorithm_visualizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Replay
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import me.brisson.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme
import me.brisson.algorithm_visualizer.ui.theme.background_dark
import me.brisson.algorithm_visualizer.ui.theme.stroke

@Composable
fun AlgorithmController(
    modifier: Modifier = Modifier,
    isAvailable: Boolean,
    isPlaying: Boolean,
    hasFinished: Boolean,
    onPlayPause: () -> Unit,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onSpeed: (Float) -> Unit,
) {
    var speedValue by remember { mutableFloatStateOf(1f) }

    val playPauseIcon: ImageVector =
        if (hasFinished) {
            Icons.Rounded.Replay
        } else if (isPlaying) {
            Icons.Rounded.Pause
        } else {
            Icons.Rounded.PlayArrow
        }

    var columnHeight by remember { mutableIntStateOf(0) }
    var showVolumePopup by remember { mutableStateOf(false) }

    if (showVolumePopup) {
        SpeedControlPopUp(
            heightOffset = columnHeight,
            onSelect = { speedValue = it; onSpeed(it) },
            onDismissRequest = { showVolumePopup = false }
        )
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(background_dark)
            .padding(horizontal = 20.dp)
            .onGloballyPositioned { coordinates ->
                columnHeight = coordinates.size.height
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        SpeedController(
            value = speedValue,
            onClick = { showVolumePopup = !showVolumePopup }
        )

        if (isAvailable) {
            IconButton(onClick = onPlayPause) {
                Icon(
                    imageVector = playPauseIcon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.size(24.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            IconButton(onClick = onPrevious) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }

            IconButton(onClick = onNext) {
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}

@Composable
private fun SpeedControlPopUp(
    modifier: Modifier = Modifier,
    heightOffset: Int,
    onSelect: (Float) -> Unit,
    onDismissRequest: (() -> Unit),
) {
    Popup(
        alignment = Alignment.BottomStart,
        offset = IntOffset(x = 50, y = -heightOffset),
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .width(130.dp)
                .border(width = 1.dp, color = stroke)
                .background(background_dark.copy(alpha = 0.9f))
                .padding(vertical = 8.dp, horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp),
        ) {
            val items = floatArrayOf(0.25f, 0.5f, 0.75f, 1f, 2f, 3f, 4f)
            items.forEach {
                val text: String = when (it) {
                    1f -> "Normal"
                    else -> it.toString() + "x"
                }

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(it); onDismissRequest() }
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                )

            }
        }
    }
}

@Composable
internal fun SpeedController(
    modifier: Modifier = Modifier,
    value: Float,
    onClick: () -> Unit,
) {
    val text: String = when (value) {
        1f -> "Normal"
        else -> value.toString() + "x"
    }

    TextButton(modifier = modifier.width(100.dp), onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onBackground,
        )
    }

}


@Preview
@Composable
fun AlgorithmControllerPreview() {
    AlgorithmVisualizerTheme {
        AlgorithmController(
            isAvailable = true,
            isPlaying = false,
            hasFinished = false,
            onPlayPause = { },
            onPrevious = { },
            onNext = { },
            onSpeed = { },
        )
    }
}
