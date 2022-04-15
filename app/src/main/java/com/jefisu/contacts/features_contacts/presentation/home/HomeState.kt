package com.jefisu.contacts.features_contacts.presentation.home

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType

data class HomeState(
    val contacts: List<Contact> = emptyList(),
    val contactOrder: ContactOrder = ContactOrder.Name(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false,
    val query: String = ""
)
