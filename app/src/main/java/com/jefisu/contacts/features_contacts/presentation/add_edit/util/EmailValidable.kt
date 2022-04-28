package com.jefisu.contacts.features_contacts.presentation.add_edit.util

import android.util.Patterns

fun isEmailValid(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun emailValidationError(email: String): String {
    return "Invalid email: $email"
}