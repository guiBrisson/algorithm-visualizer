package me.brisson.algorithm_visualizer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.brisson.algorithm_visualizer.ui.screen.home.HomeRoute

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
            //TODO
        }
    }
}