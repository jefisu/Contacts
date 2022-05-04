package com.jefisu.contacts.features_contacts.presentation.add_edit

import com.jefisu.contacts.core.presentation.util.UiText

data class AddEditState(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val nameErrorMessage: UiText? = null,
    val phoneErrorMessage: UiText? = null,
    val emailErrorMessage: UiText? = null
)
