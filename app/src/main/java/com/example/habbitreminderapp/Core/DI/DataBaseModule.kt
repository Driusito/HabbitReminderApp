package com.example.habbitreminderapp.Core.DI

import android.content.Context
import androidx.room.Room
import com.example.habbitreminderapp.Database.Category.CategoryDao
import com.example.habbitreminderapp.Database.HabbitReminderDataBase
import com.example.habbitreminderapp.Database.Task.TaskDao
import com.example.habbitreminderapp.Database.Task_Category.Task_CategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideTaskDao(habbitReminderDataBase: HabbitReminderDataBase):TaskDao{
        return habbitReminderDataBase.taskDao()
    }

    @Provides
    fun provideCategoryDao(habbitReminderDataBase: HabbitReminderDataBase):CategoryDao{
        return habbitReminderDataBase.categoryDao()
    }

    @Provides
    fun provideTaskCategoryDao(habbitReminderDataBase: HabbitReminderDataBase):Task_CategoryDao{
        return habbitReminderDataBase.taskCategoryDao()
    }
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext applicationContext: Context): HabbitReminderDataBase {
        return Room.databaseBuilder(
            applicationContext,
            HabbitReminderDataBase::class.java,
            "HabbitReminderDatabase"
        ).build()
    }
}

