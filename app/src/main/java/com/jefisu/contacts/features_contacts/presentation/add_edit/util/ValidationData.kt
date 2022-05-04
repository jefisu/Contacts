package com.jefisu.contacts.features_contacts.presentation.add_edit.util

import android.annotation.SuppressLint
import android.util.Patterns
import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.util.Constants
import com.jefisu.contacts.core.presentation.util.UiText

fun validateEmail(email: String): ValidationResult {
    val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    if (email.isNotBlank() && !isValid) {
        return ValidationResult(
            successful = false,
            errorMessage = UiText.DynamicString("That's not a valid email")
        )
    }
    return ValidationResult(
        successful = true
    )
}

fun validateName(name: String): ValidationResult {
    if (name.isBlank()) {
        return ValidationResult(
            successful = false,
            errorMessage = UiText.StringResource(
                resId = R.string.filling_in_the_field_is_mandatory,
                "name"
            )
        )
    }
    return ValidationResult(
        successful = true
    )
}

@SuppressLint("StringFormatMatches")
fun validatePhone(phone: String): ValidationResult {
    if (phone.isBlank()) {
        return ValidationResult(
            successful = false,
            errorMessage = UiText.StringResource(
                resId = R.string.filling_in_the_field_is_mandatory,
                "phone number"
            )
        )
    }
    if (phone.length < Constants.MAX_NUMBER_CHAR) {
        return ValidationResult(
            successful = false,
            errorMessage = UiText.StringResource(
                resId = R.string.filling_in_the_field_is_mandatory,
                Constants.MAX_NUMBER_CHAR
            )
        )
    }
    return ValidationResult(
        successful = true
    )
}