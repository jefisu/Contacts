package com.jefisu.contacts.features_contacts.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jefisu.contacts.features_contacts.domain.model.Contact

@Database(entities = [Contact::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: ContactDao

    companion object {
        const val NAME = "contact_db"
    }
}