package com.jefisu.contacts.features_contacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ContactEntity(
    val name: String,
    val phone: String,
    val email: String,
    val isFavorite: Boolean,
    @PrimaryKey(autoGenerate = true) val id: Int?
)