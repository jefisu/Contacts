package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository

class GetContact(
    private val repository: ContactRepository
) {
    suspend operator fun invoke(id: Int): Contact? {
        return repository.getContact(id)
    }
}