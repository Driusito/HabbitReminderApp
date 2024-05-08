package com.example.habbitreminderapp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.habbitreminderapp.Database.Category.CategoryDao
import com.example.habbitreminderapp.Database.Category.CategoryEntity
import com.example.habbitreminderapp.Database.Task.TaskDao
import com.example.habbitreminderapp.Database.Task.TaskEntity
import com.example.habbitreminderapp.Database.Task_Category.Task_CategoryDao
import com.example.habbitreminderapp.Database.Task_Category.Task_Category_Entity

@Database(entities = [TaskEntity::class, CategoryEntity::class,Task_Category_Entity::class], version = 3)
abstract class HabbitReminderDataBase : RoomDatabase() {
    abstract fun taskDao():TaskDao

    abstract fun categoryDao():CategoryDao

    abstract fun task_categoryDao():Task_CategoryDao
}