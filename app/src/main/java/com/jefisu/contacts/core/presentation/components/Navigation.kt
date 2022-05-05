package com.jefisu.contacts.core.presentation.components

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
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
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route,
            popEnterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.ContactInfo.route + "?id={id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            ),
            enterTransition = {
                scaleIn() + fadeIn()
            },
            exitTransition = {
                slideOutVertically { 100 } + fadeOut()
            }
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
            ),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentScope.SlideDirection.Down,
                    animationSpec = tween(500)
                )
            }
        ) {
            AddEditScreen(navController)
        }
        composable(
            route = Screen.Search.route,
            enterTransition = {
                slideIntoContainer(AnimatedContentScope.SlideDirection.Down)
            },
            exitTransition = {
                slideOutOfContainer(AnimatedContentScope.SlideDirection.Up)
            }
        ) {
            SearchScreen(navController)
        }
    }
}