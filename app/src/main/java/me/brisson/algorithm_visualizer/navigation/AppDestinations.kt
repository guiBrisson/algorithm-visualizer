package me.brisson.algorithm_visualizer.navigation

import androidx.navigation.NavHostController

object AppNavigationScreens {
    const val HOME_SCREEN = "home"
    const val SORT_ALGORITHMS_SCREEN = "sort_algorithms"
    const val SORTING_SCREEN = "sorting"
    const val SORTING_INFO_SCREEN = "sorting_info"
}

object AppNavigationArgs {
    const val SORT_ALGORITHM_ID = "sort_algorithm_id"
}

object AppNavigationRoutes {
    const val HOME_ROUTE = AppNavigationScreens.HOME_SCREEN
    const val SORT_ALGORITHMS_ROUTE = AppNavigationScreens.SORT_ALGORITHMS_SCREEN
    const val ALGORITHM_ROUTE =
        "${AppNavigationScreens.SORTING_SCREEN}/{${AppNavigationArgs.SORT_ALGORITHM_ID}}"
    const val SORTING_INFO_ROUTE =
        "${AppNavigationScreens.SORTING_INFO_SCREEN}/{${AppNavigationArgs.SORT_ALGORITHM_ID}}"
}

class AppDestinations(private val navController: NavHostController) {

    fun navigateToSortAlgorithms() {
        navController.navigate(route = AppNavigationRoutes.SORT_ALGORITHMS_ROUTE)
    }

}