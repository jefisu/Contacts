package com.jefisu.contacts.features_contacts.presentation.add_edit.components

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
fun AddEditTextField(
    text: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    trailingIcon: ImageVector = Icons.Default.Clear,
    trailingIconDescription: String = "Clear text",
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
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
        Spacer(modifier = Modifier.width(4.dp))
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text(text = hint, style = MaterialTheme.typography.h5) },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            textStyle = MaterialTheme.typography.h5,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (text.isNotBlank()) {
                    IconButton(onClick = onClickClearText) {
                        Icon(
                            imageVector = trailingIcon,
                            contentDescription = trailingIconDescription
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