package com.jefisu.contacts.features_contacts.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    val name: String = "",
    val phone: String = "",
    val email: String = "",
    val date: String = "",
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)

class InvalidContactException(message: String) : Exception(message)
