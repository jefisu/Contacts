package com.jefisu.contacts.core.presentation.util

import android.content.Context
import androidx.annotation.StringRes
import com.jefisu.contacts.R

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    companion object {
        fun unknownError(): UiText {
            return StringResource(R.string.unknown_error)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}
