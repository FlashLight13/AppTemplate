package com.example.apptemplate.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apptemplate.StubEntity

@Database(version = 1, exportSchema = false, entities = [StubEntity::class])
abstract class Database : RoomDatabase()