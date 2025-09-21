package com.example.denoapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.denoapplication.dao.PostDao
import com.example.denoapplication.model.PostResponseItem

@Database(
    entities = [PostResponseItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}