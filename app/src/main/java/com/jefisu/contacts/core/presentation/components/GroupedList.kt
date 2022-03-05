package com.jefisu.contacts.core.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.features_contacts.domain.model.Contact

@ExperimentalFoundationApi
@Composable
fun GroupedList(
    grouped: Map<String, List<Contact>>,
    item: @Composable (Contact) -> Unit = {}
) {
    LazyColumn {
        grouped.forEach { (initial, contactForInitial) ->
            stickyHeader {
                Box(
                    modifier = Modifier.padding(horizontal = 26.dp, vertical = 2.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = initial.uppercase(),
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            items(contactForInitial) {
                item(it)
            }
        }
    }
}