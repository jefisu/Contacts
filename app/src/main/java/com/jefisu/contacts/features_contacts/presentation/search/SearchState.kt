package com.jefisu.contacts.features_contacts.presentation.search

import com.jefisu.contacts.features_contacts.domain.model.Contact

data class SearchState(
    val contacts: List<Contact> = emptyList(),
    val query: String = ""
)
