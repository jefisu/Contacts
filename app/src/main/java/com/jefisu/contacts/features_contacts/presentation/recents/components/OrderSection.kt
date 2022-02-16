package com.jefisu.contacts.features_contacts.presentation.recents.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    contactOrder: ContactOrder = ContactOrder.Date(OrderType.Descending),
    onOrderChange: (ContactOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Name",
                selected = contactOrder is ContactOrder.Name,
                onSelect = { onOrderChange(ContactOrder.Name(contactOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = contactOrder is ContactOrder.Date,
                onSelect = { onOrderChange(ContactOrder.Date(contactOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = contactOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(contactOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = contactOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(contactOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}