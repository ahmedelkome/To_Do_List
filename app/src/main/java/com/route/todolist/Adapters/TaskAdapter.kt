package com.route.todolist.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.route.todolist.Todo
import com.route.todolist.databinding.ItemTaskBinding

class TaskAdapter(private var todos:List<Todo>) : Adapter<TaskAdapter.TaskViewHolder>(){
    var onTodoClickListener:onClickTodoListener? = null
    var onDeleteClick:onImageClick? = null
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
        holder.binding.dragView.setOnClickListener {
            onTodoClickListener?.onclickitem(todo,position)
        }
        holder.binding.leftView.setOnClickListener {
            onDeleteClick?.onImageClick(todo)
        }
    }

    fun updateData(newTodoList:List<Todo>){
        todos = newTodoList
        notifyDataSetChanged()
    }
    interface onClickTodoListener{
        fun onclickitem(todo: Todo,index:Int)
    }
    interface onImageClick{
        fun onImageClick(todo: Todo)
    }
    class TaskViewHolder(val binding: ItemTaskBinding) : ViewHolder(binding.root){}
}