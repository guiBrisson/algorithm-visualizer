package me.brisson.algorithm_visualizer.navigation

import androidx.navigation.NavHostController

object AppNavigationScreens {
    const val HOME_SCREEN = "home"
    const val SORT_ALGORITHMS_SCREEN = "sort_algorithms"
    const val SORTING_SCREEN = "sorting"
    const val ALGORITHM_INFO_SCREEN = "sorting_info"
}

object AppNavigationArgs {
    const val SORT_ALGORITHM_CLASS_NAME = "sort_algorithm_class_name"
    const val ALGORITHM_MD_RES_ID = "algorithm_md_res_id"
}

object AppNavigationRoutes {
    const val HOME_ROUTE = AppNavigationScreens.HOME_SCREEN
    const val SORT_ALGORITHMS_ROUTE = AppNavigationScreens.SORT_ALGORITHMS_SCREEN
    const val SORTING_ROUTE =
        "${AppNavigationScreens.SORTING_SCREEN}/{${AppNavigationArgs.SORT_ALGORITHM_CLASS_NAME}}"
    const val ALGORITHM_INFO_ROUTE =
        "${AppNavigationScreens.ALGORITHM_INFO_SCREEN}/{${AppNavigationArgs.ALGORITHM_MD_RES_ID}}"
}

class AppDestinations(private val navController: NavHostController) {

    fun navigateToSortAlgorithms() {
        navController.navigate(route = AppNavigationRoutes.SORT_ALGORITHMS_ROUTE)
    }

    fun navigateToSorting(className: String) {
        val route = "${AppNavigationScreens.SORTING_SCREEN}/$className"
        navController.navigate(route = route)
    }

    fun navigateToAlgorithmInfo(mdResId: Int) {
        val route = "${AppNavigationScreens.ALGORITHM_INFO_SCREEN}/$mdResId"
        navController.navigate(route = route)
    }

}
