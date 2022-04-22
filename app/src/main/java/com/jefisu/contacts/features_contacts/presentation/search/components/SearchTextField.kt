package com.jefisu.contacts.features_contacts.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    textStyle: TextStyle = MaterialTheme.typography.h5,
    hint: String,
    onNavigateBackClick: () -> Unit = {},
    onClickClearText: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(onClick = onNavigateBackClick) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Back to home screen"
            )
        }
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text(text = hint, style = textStyle) },
            singleLine = true,
            textStyle = textStyle,
            modifier = Modifier.weight(0.9f),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        if (text.isNotBlank()) {
            IconButton(onClick = onClickClearText) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear search text"
                )
            }
        }
    }
}