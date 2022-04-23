package com.jefisu.contacts.features_contacts.presentation.add_edit.util

import java.util.regex.Pattern

fun isEmailValid(email: String): Boolean {
    return Pattern.matches("^(.+)@(.+)\$", email)
}

fun emailValidationError(email: String): String {
    return "Invalid email: $email"
}