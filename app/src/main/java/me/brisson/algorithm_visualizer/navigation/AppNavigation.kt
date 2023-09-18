package me.brisson.algorithm_visualizer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import me.brisson.algorithm_visualizer.ui.screens.algorithm_info.AlgorithmInfoRoute
import me.brisson.algorithm_visualizer.ui.screens.home.HomeRoute
import me.brisson.algorithm_visualizer.ui.screens.sort_algorithms.SortAlgorithmsRoute
import me.brisson.algorithm_visualizer.ui.screens.sorting.SortingRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavigationRoutes.HOME_ROUTE
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppNavigationRoutes.HOME_ROUTE) {
            HomeRoute(
                onSortClick = { AppDestinations(navController).navigateToSortAlgorithms() },
            )
        }
        composable(AppNavigationRoutes.SORT_ALGORITHMS_ROUTE) {
            SortAlgorithmsRoute(
                onAlgorithm = { AppDestinations(navController).navigateToSorting(it) },
                onBack = { navController.navigateUp() },
            )
        }
        composable(
            route = AppNavigationRoutes.SORTING_ROUTE,
            arguments = listOf(navArgument(AppNavigationArgs.SORT_ALGORITHM_CLASS_NAME) {
                type = NavType.StringType
            }),
        ) {
            SortingRoute(
                onInfo = { AppDestinations(navController).navigateToAlgorithmInfo(it) },
                onBack = { navController.navigateUp() },
            )
        }
        composable(
            route = AppNavigationRoutes.ALGORITHM_INFO_ROUTE,
            arguments = listOf(navArgument(AppNavigationArgs.ALGORITHM_MD_RES_ID) {
                type = NavType.IntType
            }),
        ) {
            AlgorithmInfoRoute(
                onBack = { navController.navigateUp() }
            )
        }
    }
}
