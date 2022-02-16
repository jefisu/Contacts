package com.jefisu.contacts.features_contacts.di

import android.app.Application
import androidx.room.Room
import com.jefisu.contacts.features_contacts.data.ContactDatabase
import com.jefisu.contacts.features_contacts.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContactDatabase(app: Application): ContactDatabase {
        return Room.databaseBuilder(
            app,
            ContactDatabase::class.java,
            ContactDatabase.NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactUseCase(db: ContactDatabase): ContactsUseCase {
        return ContactsUseCase(
            getContacts = GetContacts(db.dao),
            getContact = GetContact(db.dao),
            getContactsByName = GetContactsByName(db.dao),
            insertContact = InsertContact(db.dao),
            deleteContact = DeleteContact(db.dao)
        )
    }
}