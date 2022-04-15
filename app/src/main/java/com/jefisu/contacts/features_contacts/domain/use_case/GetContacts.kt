package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.features_contacts.data.toContact
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import com.jefisu.contacts.features_contacts.domain.util.ContactOrder
import com.jefisu.contacts.features_contacts.domain.util.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetContacts(
    private val repository: ContactRepository
) {
    operator fun invoke(
        contactOrder: ContactOrder
    ): Flow<List<Contact>> {
        return repository.getContacts().map { contacts ->
            when (contactOrder.orderType) {
                is OrderType.Ascending -> contacts
                    .map { it.toContact() }
                    .sortedBy { it.name }
                is OrderType.Descending -> contacts
                    .map { it.toContact() }
                    .sortedByDescending { it.name }
            }
        }
    }
}