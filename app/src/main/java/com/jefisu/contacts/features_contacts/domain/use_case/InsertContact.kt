package com.jefisu.contacts.features_contacts.domain.use_case

import com.jefisu.contacts.core.presentation.util.Constants.MAX_NUMBER_CHAR
import com.jefisu.contacts.features_contacts.domain.model.Contact
import com.jefisu.contacts.features_contacts.domain.model.InvalidContactException
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.emailValidationError
import com.jefisu.contacts.features_contacts.presentation.add_edit.util.isEmailValid

class InsertContact(
    private val repository: ContactRepository
) {
    @Throws
    suspend operator fun invoke(contact: Contact) {
        when {
            contact.name.isBlank() -> {
                throw InvalidContactException("Preechimento do nome é obrigatório")
            }
            contact.phone.isBlank() -> {
                throw InvalidContactException("Preechimento do número é obrigatório")
            }
            contact.phone.length < MAX_NUMBER_CHAR -> {
                throw InvalidContactException("O número deve conter $MAX_NUMBER_CHAR caracteres")
            }
            contact.email.isNotBlank() && !isEmailValid(contact.email) -> {
                throw InvalidContactException(emailValidationError(contact.email))
            }
        }
        repository.insertContact(contact)
    }
}