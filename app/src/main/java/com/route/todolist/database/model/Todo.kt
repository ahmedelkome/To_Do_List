package com.route.todolist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo
    var title:String?,
    @ColumnInfo
    var description:String?,
    @ColumnInfo
    var date:Long?,
    @ColumnInfo
    var isDone:Boolean?
)
