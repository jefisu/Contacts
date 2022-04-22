package com.jefisu.contacts.features_contacts.presentation.home

import com.jefisu.contacts.features_contacts.domain.model.Contact

data class HomeState(
    val contacts: Map<String, List<Contact>> = emptyMap(),
    val listSize: Int = 0
)
