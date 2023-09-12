package me.brisson.algorithm_visualizer.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.brisson.algorithm_visualizer.ui.theme.background_dark


@Composable
fun SortAlgorithmVisualizer(
    modifier: Modifier = Modifier,
    arr: IntArray,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(background_dark)
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BarIllustration(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            arr = arr,
        )
    }
}