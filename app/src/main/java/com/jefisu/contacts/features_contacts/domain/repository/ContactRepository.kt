package com.jefisu.contacts.features_contacts.domain.repository

import com.jefisu.contacts.features_contacts.data.ContactEntity
import com.jefisu.contacts.features_contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(): Flow<List<ContactEntity>>

    suspend fun getContact(id: Int): Contact?

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)
}