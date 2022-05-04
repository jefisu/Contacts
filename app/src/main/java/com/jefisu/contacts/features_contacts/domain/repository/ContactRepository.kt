package com.jefisu.contacts.features_contacts.domain.repository

import com.jefisu.contacts.features_contacts.domain.model.Contact
import kotlinx.coroutines.flow.Flow

interface ContactRepository {

    fun getContacts(): Flow<List<Contact>>

    fun getContact(id: Int): Flow<Contact?>

    suspend fun getContactsByName(query: String): Flow<List<Contact>>

    suspend fun insertContact(contact: Contact)

    suspend fun deleteContact(contact: Contact)
}