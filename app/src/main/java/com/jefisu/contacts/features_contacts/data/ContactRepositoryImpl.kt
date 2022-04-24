package com.jefisu.contacts.features_contacts.data

import com.jefisu.contacts.R
import com.jefisu.contacts.core.presentation.util.Constants
import com.jefisu.contacts.core.presentation.util.Resource
import com.jefisu.contacts.core.presentation.util.SimpleResource
import com.jefisu.contacts.core.presentation.util.UiText
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.emailValidationError
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.isEmailValid
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
        return contactDao.getContacts()
            .map { contacts ->
                contacts
                    .map { it.toContact() }
                    .sortedBy { it.name[0] }
            }
    }

    override suspend fun getContact(id: Int): Contact {
        return contactDao.getContact(id).toContact()
    }

    override suspend fun getContactsByName(query: String): Flow<List<Contact>> {
        return when {
            query.isNotBlank() -> {
                contactDao.getContacts()
                    .map { contacts ->
                        contacts
                            .map { it.toContact() }
                            .sortedBy { it.name[0] }
                            .filter { contact ->
                                contact.name.lowercase().contains(query.lowercase())
                            }
                    }
            }
            else -> emptyFlow()
        }
    }

    override suspend fun insertContact(contact: Contact): SimpleResource {
        return when {
            contact.name.isBlank() -> {
                Resource.Error(
                    uiText = UiText.StringResource(
                        resId = R.string.filling_in_the_field_is_mandatory,
                        "name"
                    )
                )
            }
            contact.phone.isBlank() -> {
                Resource.Error(
                    uiText = UiText.StringResource(
                        resId = R.string.filling_in_the_field_is_mandatory,
                        "phone number"
                    )
                )
            }
            contact.phone.length < Constants.MAX_NUMBER_CHAR -> {
                Resource.Error(
                    uiText = UiText.StringResource(
                        resId = R.string.the_number_must_contain_characters,
                        Constants.MAX_NUMBER_CHAR
                    )
                )
            }
            contact.email.isNotBlank() && !isEmailValid(contact.email) -> {
                Resource.Error(
                    uiText = UiText.DynamicString(emailValidationError(contact.email))
                )
            }
            else -> {
                Resource.Success(
                    data = contactDao.insertContact(contact.toContactEntity())
                )
            }
        }
    }

    override suspend fun deleteContact(contact: Contact) {
        contactDao.deleteContact(contact.toContactEntity())
    }
}