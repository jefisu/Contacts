package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.data.ContactDao
import com.jefisu.contacts.features_contacts.domain.model.Contact

class GetContact(
    private val dao: ContactDao
) {
    suspend operator fun invoke(id: Int): Contact {
        return dao.getContact(id)
    }
}