package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository

class DeleteContact(
    private val repository: ContactRepository
) {
    suspend operator fun invoke(contact: Contact) {
        repository.deleteContact(contact)
    }
}