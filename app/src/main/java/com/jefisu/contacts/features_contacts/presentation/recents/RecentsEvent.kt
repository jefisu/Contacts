package com.jefisu.contacts.features_contacts.presentation.recents

import com.jefisu.contacts.features_contacts.domain.util.ContactOrder

sealed class RecentsEvent {
    data class Order(val contactOrder: ContactOrder) : RecentsEvent()
    object ToggleOrderSection : RecentsEvent()
}
