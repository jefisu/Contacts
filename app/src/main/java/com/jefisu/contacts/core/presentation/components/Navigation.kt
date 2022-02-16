package com.jefisu.contacts.core.presentation.components

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.add_edit.AddEditScreen
import com.jefisu.contacts.features_contacts.presentation.home.HomeScreen
import com.jefisu.contacts.features_contacts.presentation.recents.RecentsScreen
import com.jefisu.contacts.features_contacts.presentation.search.SearchScreen
import com.jefisu.contacts.features_contacts.presentation.splash.SplashScreen

@Composable
fun Navigation(navController: NavHostController, scaffoldState: ScaffoldState) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.AddEdit.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = null
                    nullable = true
                }
            )
        ) {
            EnterAnimation {
                AddEditScreen(navController, scaffoldState)
            }
        }
        composable(Screen.Recents.route) {
            RecentsScreen(navController)
        }
        composable(Screen.Search.route) {
            EnterAnimation {
                SearchScreen(navController)
            }
        }
    }
}