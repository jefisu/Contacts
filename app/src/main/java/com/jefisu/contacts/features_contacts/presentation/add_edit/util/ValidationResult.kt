package com.jefisu.contacts.features_contacts.presentation.add_edit.util

import com.jefisu.contacts.core.presentation.util.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)