package com.route.todolist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todolist.Todo

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: Todo)

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("select * from Todo")
    fun getTodos():List<Todo>

    @Query("select * from Todo where date = :date ")
    fun getTodosByDate(date: Long):List<Todo>
}