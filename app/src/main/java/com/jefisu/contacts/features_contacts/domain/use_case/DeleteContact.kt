package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.data.ContactDao
import com.jefisu.contacts.features_contacts.domain.model.Contact

class DeleteContact(
    private val dao: ContactDao
) {
    suspend operator fun invoke(contact: Contact) {
        dao.deleteContact(contact)
    }
}