package com.jefisu.contacts.features_contacts.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDao {

    @Query("SELECT * FROM ContactEntity ORDER BY UPPER(name) ASC")
    fun getContacts(): Flow<List<ContactEntity>>

    @Query("SELECT * FROM ContactEntity WHERE name LIKE '%' || LOWER(:query) || '%'")
    fun getContactsByName(query: String): Flow<List<ContactEntity>>

    @Query("SELECT * FROM ContactEntity WHERE id = :id")
    fun getContact(id: Int): Flow<ContactEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: ContactEntity)

    @Delete
    suspend fun deleteContact(contact: ContactEntity)
}