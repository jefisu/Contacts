package com.jefisu.contacts.features_contacts.domain.use_case

data class ContactsUseCase(
    val getContacts: GetContacts,
    val getContact: GetContact,
    val getContactsByName: GetContactsByName,
    val insertContact: InsertContact,
    val deleteContact: DeleteContact
)
