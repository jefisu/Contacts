package com.jefisu.contacts.core.presentation.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.features_contacts.presentation.add_edit.AddEditScreen
import com.jefisu.contacts.features_contacts.presentation.contact_info.ContactInfoScreen
import com.jefisu.contacts.features_contacts.presentation.home.HomeScreen
import com.jefisu.contacts.features_contacts.presentation.search.SearchScreen

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.ContactInfo.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) {
          ContactInfoScreen(navController)
        }
        composable(
            route = Screen.AddEdit.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            EnterAnimation {
                AddEditScreen(navController)
            }
        }
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
    }
}