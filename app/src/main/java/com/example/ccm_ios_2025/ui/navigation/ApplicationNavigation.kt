package com.example.ccm_ios_2025.ui.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ccm_ios_2025.ui.screen.MainScreen
import com.example.ccm_ios_2025.ui.screen.SecondScreen

@Composable
fun ApplicationNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = ApplicationNavigationPath.Home.route,
        enterTransition = { fadeIn(animationSpec = tween(700)) },
        exitTransition = { fadeOut(animationSpec = tween(700)) },
    ) {
        composable(ApplicationNavigationPath.Home.route) {
            MainScreen(
                onButtonClick = { navController.navigate(ApplicationNavigationPath.Second.route) })
        }

        composable(ApplicationNavigationPath.Second.route) {
            SecondScreen(
                navigateBack = { navController.popBackStack() })
        }
    }
}

sealed class ApplicationNavigationPath(val route: String) {
    object Home : ApplicationNavigationPath("home")
    object Second : ApplicationNavigationPath("second")
}
