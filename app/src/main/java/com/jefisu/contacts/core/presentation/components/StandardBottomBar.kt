package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.jefisu.contacts.core.presentation.util.BottomNavItem
import com.jefisu.contacts.core.presentation.util.Screen

@Composable
fun StandardBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    bottomNavItems: List<BottomNavItem> = listOf(
        BottomNavItem("Recents", Screen.Recents.route),
        BottomNavItem("Contacts", Screen.Home.route)
    )
) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {
        bottomNavItems.forEach { item ->
            val selected = item.route == backStackEntry?.destination?.route
            StandardButton(
                text = item.title,
                selected = selected,
                onClickAction = {
                    navController.navigate(item.route) {
                        popUpTo(Screen.Home.route)
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}