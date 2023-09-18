package me.brisson.algorithm_visualizer.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var _runNewValuesUpdate = true

    fun repeatUpdatingNewValues() = viewModelScope.launch {
        _runNewValuesUpdate = true
        while (_runNewValuesUpdate) {
            Log.d(TAG, "Updating new values")
            _uiState.update {
                it.copy(sortingIntList = List(6) {
                    Random.nextInt(
                        from = 15,
                        until = 50
                    )
                })
            }
            delay(1500)
        }
    }

    fun clear() {
        _runNewValuesUpdate = false
        Log.d(TAG, "No longer updating new values")
    }

    companion object {
        private val TAG = HomeViewModel::class.java.simpleName
    }

}
