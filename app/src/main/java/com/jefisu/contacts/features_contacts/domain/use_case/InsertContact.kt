package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.data.ContactDao
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.model.InvalidContactException

class InsertContact(
    private val dao: ContactDao
) {
    @Throws
    suspend operator fun invoke(contact: Contact) {
        if (contact.name.isBlank() || contact.phone.isBlank()) {
            throw InvalidContactException("Preechimento dos campos é obrigatório")
        }
        dao.insertContact(contact)
    }
}