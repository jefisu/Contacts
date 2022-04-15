package com.jefisu.contacts.features_contacts.di

import com.jefisu.contacts.features_contacts.data.ContactRepositoryImpl
import com.jefisu.contacts.features_contacts.domain.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindContactRepository(
        repository: ContactRepositoryImpl
    ): ContactRepository
}