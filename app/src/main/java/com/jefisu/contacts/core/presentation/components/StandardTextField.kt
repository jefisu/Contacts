package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun StandardTextField(
    text: String,
    onTextChange: (String) -> Unit,
    icon1: ImageVector,
    icon2: ImageVector = Icons.Default.Clear,
    icon2Description: String = "Clear text",
    hint: String,
    onClick: () -> Unit = {},
    onClickClearText: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions()
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon1,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text(text = hint) },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            textStyle = MaterialTheme.typography.h6,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (text.isNotBlank()) {
                    IconButton(onClick = onClickClearText) {
                        Icon(
                            imageVector = icon2,
                            contentDescription = icon2Description
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}