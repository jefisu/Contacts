package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.model.InvalidContactException
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository

class InsertContact(
    private val repository: ContactRepository
) {
    @Throws
    suspend operator fun invoke(contact: Contact) {
        if (contact.name.isBlank() || contact.phone.isBlank()) {
            throw InvalidContactException("Preechimento dos campos é obrigatório")
        }
        repository.insertContact(contact)
    }
}