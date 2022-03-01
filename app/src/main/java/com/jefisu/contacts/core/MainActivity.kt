package com.jefisu.contacts.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jefisu.contacts.core.presentation.components.Navigation
import com.jefisu.contacts.core.presentation.util.Screen
import com.jefisu.contacts.core.presentation.components.StandardScaffold
import com.jefisu.contacts.core.presentation.ui.theme.ContactsTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactsTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val scaffoldState = rememberScaffoldState()

                StandardScaffold(
                    modifier = Modifier.fillMaxSize(),
                    navController = navController,
                    state = scaffoldState,
                    showBottomBar = shouldShowBottomBar(navBackStackEntry),
                    content = {
                        Navigation(navController, scaffoldState)
                    }
                )
            }
        }
    }

    private fun shouldShowBottomBar(navBackStackEntry: NavBackStackEntry?): Boolean {
        return navBackStackEntry?.destination?.route in listOf(
            Screen.Home.route,
            Screen.Recents.route
        )
    }
}