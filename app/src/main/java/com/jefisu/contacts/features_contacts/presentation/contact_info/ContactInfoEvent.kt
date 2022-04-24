package com.jefisu.contacts.features_contacts.presentation.contact_info

import com.jefisu.contacts.features_contacts.domain.model.Contact

sealed class ContactInfoEvent {
    data class AddRemoveFavorite(val selected: Boolean, val contact: Contact) : ContactInfoEvent()
}