package me.brisson.algorithm_visualizer.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun BarIllustration(
    modifier: Modifier = Modifier,
) {
    val array = IntArray(6) { Random.nextInt(from = 15, until = 50) }

    LazyRow(
        modifier = modifier.height(50.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Bottom,
    ) {
        items(array.toTypedArray()) { i ->
            BarItem(modifier = Modifier, height = i.dp)
        }
    }
}

@Composable
fun BarItem(
    modifier: Modifier = Modifier,
    height: Dp,
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
            .background(MaterialTheme.colorScheme.primary),
        content = { },
    )
}