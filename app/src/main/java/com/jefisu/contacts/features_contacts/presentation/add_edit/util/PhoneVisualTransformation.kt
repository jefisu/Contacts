package com.jefisu.contacts.features_contacts.presentation.add_edit.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun phoneVisualTransformation(value: AnnotatedString): TransformedText {
    val trimmed =
        if (value.length >= 13) value.substring(0..12) else value

    val annotatedString = buildAnnotatedString {
        trimmed.indices.forEach { i ->
            when (i) {
                0 -> append("+")
                2 -> append(" ")
                4 -> append(" ")
                9 -> append("-")
            }
            append(trimmed[i])
        }
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when {
                offset == 0 -> offset
                offset <= 2 -> offset + 1
                offset <= 4 -> offset + 2
                offset <= 9 -> offset + 3
                offset <= 13 -> offset + 4
                else -> 17
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return offset
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}