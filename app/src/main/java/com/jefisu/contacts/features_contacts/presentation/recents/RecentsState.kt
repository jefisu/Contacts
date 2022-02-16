package com.jefisu.contacts.features_contacts.presentation.recents

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType

data class RecentsState(
    val contacts: List<Contact> = emptyList(),
    val contactOrder: ContactOrder = ContactOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
