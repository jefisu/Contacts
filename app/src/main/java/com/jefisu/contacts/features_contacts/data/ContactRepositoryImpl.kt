package com.jefisu.contacts.features_contacts.data

import com.jefisu.contacts.core.presentation.util.Constants
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.model.InvalidContactException
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.emailValidationError
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.isEmailValid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactRepositoryImpl @Inject constructor(
    db: ContactDatabase
) : ContactRepository {

    private val contactDao = db.dao

    override fun getContacts(): Flow<List<Contact>> {
        return contactDao.getContacts()
            .map { contacts ->
                contacts
                    .map { it.toContact() }
                    .sortedBy { it.name }
            }
    }

    override suspend fun getContact(id: Int): Contact? {
        return contactDao.getContact(id)?.toContact()
    }

    override suspend fun getContactsByName(query: String): Flow<List<Contact>> {
        return when {
            query.isNotBlank() -> {
                contactDao.getContacts()
                    .map { contacts ->
                        contacts
                            .map { it.toContact() }
                            .sortedBy { it.name }
                            .filter { contact ->
                                contact.name.lowercase().contains(query.lowercase())
                            }
                    }
            }
            else -> flow { }
        }
    }

    @Throws
    override suspend fun insertContact(contact: Contact) {
        when {
            contact.name.isBlank() -> {
                throw InvalidContactException("Preechimento do nome é obrigatório")
            }
            contact.phone.isBlank() -> {
                throw InvalidContactException("Preechimento do número é obrigatório")
            }
            contact.phone.length < Constants.MAX_NUMBER_CHAR -> {
                throw InvalidContactException("O número deve conter ${Constants.MAX_NUMBER_CHAR} caracteres")
            }
            contact.email.isNotBlank() && !isEmailValid(contact.email) -> {
                throw InvalidContactException(emailValidationError(contact.email))
            }
        }
        contactDao.insertContact(contact.toContactEntity())
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact.toContactEntity())
    }
}