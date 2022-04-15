package com.jefisu.contacts.features_contacts.presentation.home

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder

sealed class HomeEvent {
    data class AddRemoveFavorite(val selected: Boolean, val contact: Contact) : HomeEvent()
    data class DeleteContact(val contact: Contact) : HomeEvent()
    data class Order(val contactOrder: ContactOrder) : HomeEvent()
    object ToggleOrderSection : HomeEvent()
    data class ClearResearchText(val text: String = "") : HomeEvent()
    data class OnFilterContacts(val query: String) : HomeEvent()
}
