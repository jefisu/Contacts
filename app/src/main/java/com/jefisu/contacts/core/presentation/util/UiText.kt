package com.jefisu.contacts.core.presentation.util

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    class StringResource(@StringRes val resId: Int): UiText()

    @Composable
    fun asString(): String {
        return when(this) {
            is StringResource -> stringResource(resId)
        }
    }
}
