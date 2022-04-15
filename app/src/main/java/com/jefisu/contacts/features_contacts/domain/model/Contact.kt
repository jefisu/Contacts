package com.jefisu.contacts.features_contacts.domain.model

import androidx.room.Entity

@Entity
data class Contact(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val isFavorite: Boolean = false,
    val id: Int? = null
)

class InvalidContactException(message: String) : Exception(message)