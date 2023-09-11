package me.brisson.algorithm_visualizer.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColorScheme = darkColorScheme(
    primary = primary,
    background = background,
    onBackground = Color.White,
    surface = surface,
    onSurface = Color.White,
)


@Composable
fun AlgorithmVisualizerTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = darkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
