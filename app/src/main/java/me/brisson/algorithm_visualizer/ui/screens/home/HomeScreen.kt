package me.brisson.algorithm_visualizer.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.algorithm_visualizer.R
import me.brisson.algorithm_visualizer.algorithms.sort.utils.SortingAlgorithms
import me.brisson.algorithm_visualizer.algorithms.utils.ChartState
import me.brisson.algorithm_visualizer.ui.components.BarIllustration
import me.brisson.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme
import me.brisson.algorithm_visualizer.ui.theme.stroke
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
    onSortClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.repeatUpdatingNewValues()
    }

    DisposableEffect(Unit) {
        onDispose { viewModel.clear() }
    }

    HomeScreen(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onSortClick = onSortClick
    )
}

@Composable
internal fun HomeScreen(
    modifier: Modifier,
    uiState: HomeUiState,
    onSortClick: () -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                modifier = Modifier.padding(top = 40.dp, bottom = 8.dp),
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        item {
            val sortingAlgorithmsListAmount = enumValues<SortingAlgorithms>().size

            GridItem(
                title = "Sorting",
                algorithmsAmount = sortingAlgorithmsListAmount,
                illustration = {
                    BarIllustration(
                        modifier = Modifier.height(50.dp),
                        chartState = ChartState(
                            arr = uiState.sortingIntList
                        )
                    )
                },
                onClick = onSortClick
            )
        }
    }
}

@Composable
fun GridItem(
    modifier: Modifier = Modifier,
    title: String,
    algorithmsAmount: Int,
    illustration: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Column(
        modifier
            .clip(RoundedCornerShape(8.dp))
            .border(width = 1.dp, color = stroke, RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
    ) {
        illustration()
        Text(
            modifier = Modifier.padding(bottom = 4.dp, top = 12.dp),
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Text(
            text = "$algorithmsAmount alhorithms",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.4f),
        )
    }
}


@Preview
@Composable
private fun PreviewHomeScreen() {
    AlgorithmVisualizerTheme {
        val uiState = HomeUiState(
            sortingIntList = listOf(40, 12, 46, 23, 50, 1),
        )
        HomeScreen(modifier = Modifier.fillMaxSize(), uiState = uiState, onSortClick = { })
    }
}
