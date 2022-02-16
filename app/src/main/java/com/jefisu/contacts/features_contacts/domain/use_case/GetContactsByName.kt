package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.data.ContactDao
import com.jefisu.contacts.features_contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetContactsByName(
    private val dao: ContactDao
) {
    operator fun invoke(): Flow<List<Contact>> {
        return dao.getContacts()
            .map { contacts ->
                contacts.sortedBy { it.name }
            }
    }
}