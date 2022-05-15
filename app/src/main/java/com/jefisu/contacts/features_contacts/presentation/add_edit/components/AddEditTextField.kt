package com.jefisu.contacts.features_contacts.presentation.add_edit.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.AddEditTextField(
    text: String,
    onTextChange: (String) -> Unit,
    icon: ImageVector,
    trailingIcon: ImageVector = Icons.Default.Clear,
    trailingIconDescription: String = "Clear text",
    hint: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onClickClearText: () -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    errorMessage: String?,
    testTag: String
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(text = hint, style = MaterialTheme.typography.h5) },
        keyboardOptions = keyboardOptions,
        singleLine = true,
        shape = RoundedCornerShape(10.dp),
        textStyle = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .testTag(testTag),
        visualTransformation = visualTransformation,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        },
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
            textColor = MaterialTheme.colors.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
    if (errorMessage != null) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            modifier = Modifier.align(Alignment.End)
        )
    }
}