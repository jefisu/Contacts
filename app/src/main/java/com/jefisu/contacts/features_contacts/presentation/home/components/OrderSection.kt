package com.jefisu.contacts.features_contacts.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    contactOrder: ContactOrder = ContactOrder.Name(OrderType.Descending),
    onOrderChange: (ContactOrder) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth()
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