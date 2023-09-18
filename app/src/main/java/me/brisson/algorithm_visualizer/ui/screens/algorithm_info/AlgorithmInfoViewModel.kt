package me.brisson.algorithm_visualizer.ui.screens.algorithm_info

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import me.brisson.algorithm_visualizer.navigation.AppNavigationArgs
import java.io.BufferedReader

class AlgorithmInfoViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _mdResId: Int =
        checkNotNull(savedStateHandle[AppNavigationArgs.ALGORITHM_MD_RES_ID])

    private val _uiState = MutableStateFlow(AlgorithmInfoUiState())
    val uiState: StateFlow<AlgorithmInfoUiState> = _uiState.asStateFlow()

    fun updateMdUiState(context: Context) {
        if (_mdResId != -1) {
            val inputStream = context.resources.openRawResource(_mdResId)
            val bufferedReader = inputStream.bufferedReader()
            val mdAsString = bufferedReader.use(BufferedReader::readText)
            _uiState.update { it.copy(markdownText = mdAsString) }
        }
    }

}
