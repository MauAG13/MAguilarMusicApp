package com.example.maguilarmusicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.maguilarmusicapp.ui.screens.DetailScreen
import com.example.maguilarmusicapp.ui.screens.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
data class DetailRoute(val id: String)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeRoute) {
        composable<HomeRoute> {
            HomeScreen(onAlbumClick = { id ->
                navController.navigate(DetailRoute(id))
            })
        }
        composable<DetailRoute> { backStackEntry ->
            val detail: DetailRoute = backStackEntry.toRoute()
            DetailScreen(
                albumId = detail.id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
