package com.jefisu.contacts.features_contacts.di

import android.app.Application
import androidx.room.Room
import com.jefisu.contacts.core.SplashScreen
import com.jefisu.contacts.features_contacts.data.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Singleton
    fun provideSplashScreen() = SplashScreen()

    @Provides
    @Singleton
    fun provideContactDatabase(app: Application): ContactDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            ContactDatabase::class.java
        ).build()
    }
}