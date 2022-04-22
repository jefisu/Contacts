package com.jefisu.contacts.features_contacts.presentation.home

import com.jefisu.contacts.features_contacts.domain.model.Contact

sealed class HomeEvent {
    data class AddRemoveFavorite(val selected: Boolean, val contact: Contact) : HomeEvent()
    data class DeleteContact(val contact: Contact) : HomeEvent()
}
