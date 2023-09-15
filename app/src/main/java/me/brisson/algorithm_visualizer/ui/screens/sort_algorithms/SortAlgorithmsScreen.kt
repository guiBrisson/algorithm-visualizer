package me.brisson.algorithm_visualizer.ui.screens.sort_algorithms

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.brisson.algorithm_visualizer.R
import me.brisson.algorithm_visualizer.algorithms.sort.utils.SortingAlgorithms
import me.brisson.algorithm_visualizer.ui.theme.AlgorithmVisualizerTheme
import me.brisson.algorithm_visualizer.ui.theme.stroke
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SortAlgorithmsRoute(
    modifier: Modifier = Modifier,
    viewModel: SortAlgorithmsViewModel = viewModel(),
    onAlgorithm: (className: String) -> Unit,
    onBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.updateSortAlgorithmsList()
    }

    SortAlgorithmsScreen(
        modifier = modifier.fillMaxSize(),
        uiState = uiState,
        onAlgorithm = onAlgorithm,
        onBack = onBack,
    )
}

@Composable
internal fun SortAlgorithmsScreen(
    modifier: Modifier,
    uiState: SortAlgorithmsUiState,
    onAlgorithm: (className: String) -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp),
    ) {
        IconButton(
            modifier = Modifier
                .offset(x = (-12).dp)
                .padding(top = 4.dp, bottom = 8.dp), onClick = onBack
        ) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground,
            )
        }

        Text(
            text = stringResource(id = R.string.sorting_title),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Text(
            modifier = Modifier.padding(top = 8.dp, bottom = 28.dp),
            text = stringResource(id = R.string.sorting_subtitle),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.69f),
        )

        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = "Algorithms",
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
        )

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(uiState.sortingList) { algorithm ->
                AlgorithmItem(
                    modifier = Modifier.fillMaxWidth(),
                    title = algorithm.algorithmClass.algorithmName,
                    onClick = {
                        val className = algorithm.algorithmClass::class.java.name
                        onAlgorithm(className)
                    },
                )
            }
        }

    }
}

@Composable
fun AlgorithmItem(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .border(width = 1.dp, color = stroke, shape = RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )

        IconButton(modifier = Modifier.size(20.dp), onClick = onClick) {
            Icon(
                imageVector = Icons.Rounded.ArrowForward,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.69f),
            )
        }
    }
}

@Preview
@Composable
fun PreviewSortAlgorithmsScreen() {
    AlgorithmVisualizerTheme {
        val uiState = SortAlgorithmsUiState(
            sortingList = enumValues<SortingAlgorithms>().toList()
        )

        SortAlgorithmsScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            onAlgorithm = { },
            onBack = { },
        )
    }
}