package com.example.apptemplate.domain.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context.applicationContext,
            Database::class.java,
            "database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}