package com.jefisu.contacts.features_contacts.data

import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepositoryImpl @Inject constructor(
    db: ContactDatabase
) : ContactRepository {

    private val contactDao = db.dao

    override fun getContacts(): Flow<List<ContactEntity>> {
        return contactDao.getContacts()
    }

    override suspend fun getContact(id: Int): Contact? {
        return contactDao.getContact(id)?.toContact()
    }

    override suspend fun insertContact(contact: Contact) {
        contactDao.insertContact(contact.toContactEntity())
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact.toContactEntity())
    }
}