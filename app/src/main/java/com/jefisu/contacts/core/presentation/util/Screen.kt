package com.jefisu.contacts.core.presentation.util

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Search : Screen("search_screen")
    object AddEdit : Screen("add_edit_screen")
    object ContactInfo : Screen("contact_info_screen")

    fun navArg(arg: Int?): String {
        return "$route?id=$arg"
    }
}
