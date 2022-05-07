package com.jefisu.contacts.features_contacts.presentation.home

import com.jefisu.contacts.features_contacts.domain.model.Contact

sealed class HomeEvent {
    data class OnSelectContact(val contact: Contact) : HomeEvent()
    object OnSelectAllContacts: HomeEvent()
    object DeleteContact : HomeEvent()
}
