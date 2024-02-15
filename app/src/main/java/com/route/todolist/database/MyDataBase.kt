package com.route.todolist.database

import android.app.Application
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.DeleteColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.route.todolist.Todo

@Database(entities = [Todo::class], version = 1)
abstract class MyDataBase: RoomDatabase() {
    abstract fun getTodoDao():TodoDao

    companion object{
        const val DATABASE_NAEM = "My DataBase"

        var database:MyDataBase?=null

        fun getInstance(context:Context):MyDataBase{
            if (database==null)
            {
                database = Room.databaseBuilder(context.applicationContext,
                    MyDataBase::class.java,
                    DATABASE_NAEM)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return database!!
        }
    }
}