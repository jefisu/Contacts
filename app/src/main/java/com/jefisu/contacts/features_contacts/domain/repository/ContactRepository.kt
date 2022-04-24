package com.jefisu.contacts.features_contacts.domain.repository

import com.jefisu.contacts.core.presentation.util.SimpleResource
import com.jefisu.contacts.features_contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(): Flow<List<Contact>>

    suspend fun getContact(id: Int): Contact

    suspend fun getContactsByName(query: String = ""): Flow<List<Contact>>

    suspend fun insertContact(contact: Contact): SimpleResource

    suspend fun deleteContact(contact: Contact)
}