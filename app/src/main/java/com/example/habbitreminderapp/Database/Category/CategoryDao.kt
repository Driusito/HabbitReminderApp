package com.example.habbitreminderapp.Database.Category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {

    @Query("Select * from CategoryEntity")
    fun getCategories():List<CategoryEntity>

    @Insert
    suspend fun addCategory(item: CategoryEntity)
}