package com.jefisu.contacts.features_contacts.presentation.home

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType

data class HomeState(
    val contacts: Map<String, List<Contact>> = emptyMap(),
    val contactOrder: ContactOrder = ContactOrder.Name(OrderType.Descending),
    val query: String = ""
)
