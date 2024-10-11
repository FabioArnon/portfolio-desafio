package br.com.campidelli.portfoliodesafio

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = "tabs") {
        composable("tabs") { MainScreen(navController) }
        composable("webView/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            WebViewScreen(navController, url)
        }
    }
}