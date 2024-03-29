package me.brisson.algorithm_visualizer.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.brisson.algorithm_visualizer.algorithms.utils.ChartIndicatorType
import me.brisson.algorithm_visualizer.algorithms.utils.ChartState
import me.brisson.algorithm_visualizer.ui.theme.selectColor
import me.brisson.algorithm_visualizer.ui.theme.swapColor

@Composable
fun BarIllustration(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(4.dp),
    chartState: ChartState,
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.Bottom,
    ) {
        itemsIndexed(chartState.arr.toTypedArray()) { index, item ->
            val indicesIndicatorColor: Color =
                if (chartState.indicesIndicators != null &&
                    chartState.indicesIndicators.first.contains(index)
                ) {
                    when (chartState.indicesIndicators.second) {
                        ChartIndicatorType.SELECT -> selectColor
                        ChartIndicatorType.SWAP -> swapColor
                    }
                } else {
                    MaterialTheme.colorScheme.primary
                }

            BarItem(modifier = Modifier, height = item.dp, color = indicesIndicatorColor)
        }
    }
}

@Composable
fun BarItem(
    modifier: Modifier = Modifier,
    height: Dp,
    color: Color = MaterialTheme.colorScheme.primary
) {
    var componentHeight by remember { mutableStateOf(0.dp) }

    LaunchedEffect(key1 = height) {
        componentHeight = height
    }

    Box(
        modifier = modifier
            .animateContentSize()
            .clip(RoundedCornerShape(2.dp))
            .size(height = componentHeight, width = 12.dp)
            .background(color),
        content = { },
    )
}
