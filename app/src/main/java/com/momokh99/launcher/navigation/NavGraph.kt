package com.momokh99.launcher.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.momokh99.launcher.ui.screens.HomeScreen
import com.momokh99.launcher.ui.screens.BranchScreen
import com.momokh99.launcher.viewmodel.LauncherViewModel

@Composable
fun LauncherNavGraph(
    viewModel: LauncherViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                viewModel = viewModel,
                onBranchClick = { branchName ->
                    navController.navigate(Routes.getBranchRoute(branchName))
                }
            )
        }
        
        composable(
            route = Routes.BRANCH,
            arguments = listOf(navArgument("branchName") { type = NavType.StringType })
        ) { backStackEntry ->
            val branchName = backStackEntry.arguments?.getString("branchName") ?: return@composable
            BranchScreen(
                viewModel = viewModel,
                branchName = branchName,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
