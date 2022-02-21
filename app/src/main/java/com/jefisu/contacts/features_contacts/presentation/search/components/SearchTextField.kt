package com.jefisu.contacts.features_contacts.presentation.search.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    text: String,
    onTextChange: (String) -> Unit,
    leadingIcon: ImageVector = Icons.Default.ArrowBack,
    leadingIconDescription: String = "Navigate back",
    hint: String,
    onClickClearText: () -> Unit = {},
    onClickBack: () -> Unit = {},
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text(text = hint, style = MaterialTheme.typography.h5) },
            singleLine = true,
            maxLines = 1,
            shape = RoundedCornerShape(10.dp),
            textStyle = MaterialTheme.typography.h5,
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                IconButton(onClick = onClickBack) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = leadingIconDescription
                    )
                }
            },
            trailingIcon = {
                if (text.isNotBlank()) {
                    IconButton(onClick = onClickClearText) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Text"
                        )
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onBackground,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
    }
}