package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun StandardScaffold(
    navController: NavController,
    modifier: Modifier = Modifier,
    showBottomBar: Boolean = true,
    state: ScaffoldState,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        scaffoldState = state,
        modifier = modifier,
        content = content,
        bottomBar = {
            if (showBottomBar) {
                StandardBottomBar(
                    navController = navController,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}