package com.jefisu.contacts.features_contacts.data

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepositoryImpl @Inject constructor(
    db: ContactDatabase
) : ContactRepository {

    private val contactDao = db.dao

    override fun getContacts(): Flow<List<Contact>> {
        return contactDao.getContacts().map { contacts ->
            contacts.map { it.toContact() }
        }
    }

    override fun getContact(id: Int): Flow<Contact?> {
        return contactDao.getContact(id).map { it?.toContact() }
    }

    override suspend fun getContactsByName(query: String): Flow<List<Contact>> {
        if (query.isBlank()) {
            return emptyFlow()
        }
        return contactDao.getContactsByName(query).map { contacts ->
            contacts.map { it.toContact() }
        }
    }

    override suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact.toContactEntity())
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact.toContactEntity())
    }
}