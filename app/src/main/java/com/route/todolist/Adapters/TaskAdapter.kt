package com.route.todolist.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.green
import androidx.core.graphics.toColor
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todolist.R
import com.route.todolist.Todo
import com.route.todolist.databinding.ItemTaskBinding

class TaskAdapter(private var todos:List<Todo>) : Adapter<TaskAdapter.TaskViewHolder>(){
    var onTodoClickListener:oneditclicklistener? = null
    var onDeleteClick:ondeleteClick? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context)
        ,parent,false)

        return TaskViewHolder(binding)

    }

    override fun getItemCount(): Int = todos.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        var todo = todos[position]
        holder.binding.titleTask.text = todos[position].title
        holder.binding.descriptionTask.text = todos[position].description
        holder.binding.rightViewEdit.setOnClickListener {
            onTodoClickListener?.onedititem(todo,position)
        }
        holder.binding.leftViewDelete.setOnClickListener {
            onDeleteClick?.onImagedeletClick(todo)
        }
        holder.binding.checkComplete.setOnClickListener {
            holder.binding.checkComplete.setBackgroundResource(R.drawable.done)
            holder.binding.titleTask.setTextColor(Color.GREEN)
        }
    }

    fun updateData(newTodoList:List<Todo>){
        todos = newTodoList
        notifyDataSetChanged()
    }
    interface oneditclicklistener{
        fun onedititem(todo: Todo,index:Int)
    }
    interface ondeleteClick{
        fun onImagedeletClick(todo: Todo)
    }
    class TaskViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root){}
}