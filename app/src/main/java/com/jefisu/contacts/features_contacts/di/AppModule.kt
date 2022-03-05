package com.jefisu.contacts.features_contacts.di

import android.app.Application
import androidx.room.Room
import com.jefisu.contacts.features_contacts.data.ContactDatabase
import com.jefisu.contacts.features_contacts.data.ContactRepositoryImpl
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
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
    fun provideContactRepository(db: ContactDatabase): ContactRepository {
        return ContactRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideContactUseCase(repository: ContactRepository): ContactsUseCase {
        return ContactsUseCase(
            getContacts = GetContacts(repository),
            getContact = GetContact(repository),
            getContactsByName = GetContactsByName(repository),
            insertContact = InsertContact(repository),
            deleteContact = DeleteContact(repository)
        )
    }
}