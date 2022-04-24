package com.jefisu.contacts.features_contacts.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM ContactEntity")
    fun getContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    suspend fun getContact(id: Int): ContactEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactEntity)

    @Delete
    suspend fun deleteContact(contact: ContactEntity)
}