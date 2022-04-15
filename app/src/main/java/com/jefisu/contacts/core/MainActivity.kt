package com.jefisu.contacts.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAddAlt
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jefisu.contacts.core.presentation.components.Navigation
import com.jefisu.contacts.core.presentation.ui.theme.ContactsTheme
import com.jefisu.contacts.core.presentation.ui.theme.NavyBlue
import com.jefisu.contacts.core.presentation.util.Screen
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

                Scaffold(
                    scaffoldState = scaffoldState,
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        val enabledFab = navBackStackEntry?.destination?.route in listOf(Screen.Home.route)
                        if (enabledFab) {
                            FloatingActionButton(
                                onClick = { navController.navigate(Screen.AddEdit.route) },
                                backgroundColor = NavyBlue
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PersonAddAlt,
                                    contentDescription = "Add contact",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                ) {
                    Navigation(navController, scaffoldState)
                }
            }
        }
    }
}