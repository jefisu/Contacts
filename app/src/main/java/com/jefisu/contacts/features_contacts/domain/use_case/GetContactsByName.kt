package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.data.toContact
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetContactsByName(
    private val repository: ContactRepository
) {
    operator fun invoke(): Flow<List<Contact>> {
        return repository.getContacts()
            .map { contacts ->
                contacts
                    .map { it.toContact() }
                    .sortedBy { it.name }
            }
    }
}