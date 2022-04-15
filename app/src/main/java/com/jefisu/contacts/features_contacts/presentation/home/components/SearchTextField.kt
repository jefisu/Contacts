package com.jefisu.contacts.features_contacts.presentation.home.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.core.presentation.ui.theme.BlueAlternative

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    hint: String,
    onClickClearText: () -> Unit = {},
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(text = hint) },
        singleLine = true,
        maxLines = 1,
        shape = RoundedCornerShape(16.dp),
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search contact"
            )
        },
        trailingIcon = {
            if (text.isNotBlank()) {
                IconButton(onClick = onClickClearText) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear text"
                    )
                }
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BlueAlternative
        )
    )
}