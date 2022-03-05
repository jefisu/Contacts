package com.jefisu.contacts.features_contacts.domain.use_case

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
                is OrderType.Ascending -> {
                    when (contactOrder) {
                        is ContactOrder.Name -> contacts.sortedBy { it.name }
                        is ContactOrder.Date -> contacts.sortedBy { it.date }
                    }
                }
                is OrderType.Descending -> {
                    when(contactOrder) {
                        is ContactOrder.Name -> contacts.sortedByDescending { it.name }
                        is ContactOrder.Date -> contacts.sortedByDescending { it.date }
                    }
                }
            }
        }
    }
}