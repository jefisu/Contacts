package com.jefisu.contacts.features_contacts.presentation.contact_info

sealed class ContactInfoEvent {
    object AddRemoveFavorite: ContactInfoEvent()
}