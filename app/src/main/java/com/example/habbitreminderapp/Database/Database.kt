package com.example.habbitreminderapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habbitreminderapp.Database.Category.CategoryEntity
import com.example.habbitreminderapp.Database.Task.TaskDao
import com.example.habbitreminderapp.Database.Task.TaskEntity

@Database(entities = [TaskEntity::class, CategoryEntity::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun taskDao():TaskDao
}