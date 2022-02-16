package com.jefisu.contacts.features_contacts.presentation.add_edit

sealed class AddEditEvent {
    object AddContact : AddEditEvent()
    data class EnteredName(val name: String) : AddEditEvent()
    data class EnteredPhone(val phone: String) : AddEditEvent()
    data class EnteredEmail(val email: String) : AddEditEvent()
    object ClearTextName : AddEditEvent()
    object ClearTextPhone : AddEditEvent()
    object ClearTextEmail : AddEditEvent()
}
