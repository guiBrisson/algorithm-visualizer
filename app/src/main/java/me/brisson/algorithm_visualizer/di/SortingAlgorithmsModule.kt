package me.brisson.algorithm_visualizer.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.brisson.algorithm_visualizer.algorithms.sort.InsertionSort

@Module
@InstallIn(ViewModelComponent::class)
object SortingAlgorithmsModule {

    @Provides
    fun providesInsertionSort(): InsertionSort = InsertionSort()

}
