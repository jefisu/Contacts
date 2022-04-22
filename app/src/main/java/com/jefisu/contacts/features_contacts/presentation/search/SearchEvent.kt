package com.jefisu.contacts.features_contacts.presentation.search

sealed class SearchEvent {
    object ClearSearchText : SearchEvent()
    data class OnFilterContacts(val query: String) : SearchEvent()
}
