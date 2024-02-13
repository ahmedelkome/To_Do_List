package com.route.todolist.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.route.todolist.Adapters.TaskAdapter
import com.route.todolist.Constant.Constants
import com.route.todolist.Todo
import com.route.todolist.database.MyDataBase
import com.route.todolist.databinding.FragmentTaskBinding
import com.route.todolist.CalenderExt.timemillies
import com.route.todolist.ui.screen.EditTaskActivity


class TaskFragment : Fragment() {
    lateinit var binding: FragmentTaskBinding
    val adapter = TaskAdapter(listOf())
    var currentDate = CalendarDay.today()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateToEditTodo()
        binding.recTodo.adapter = adapter
        binding.idCalender.selectedDate = currentDate
        binding.idCalender.setOnDateChangedListener { widget, date, selected ->
            currentDate = date
            refreshDataTodo()
        }
        deletData()
        refreshDataTodo()
    }

    private fun deletData() {
        adapter.onDeleteClick = object : TaskAdapter.onImageClick{
            override fun onImageClick(todo: Todo) {
                MyDataBase.getInstance(requireContext()).getTodoDao().deleteTodo(todo)
                binding.recTodo.adapter = adapter
                refreshDataTodo()
            }
        }
    }

    fun refreshDataTodo() {
        var newTaskTodo = MyDataBase
            .getInstance(requireActivity())
            .getTodoDao().getTodosByDate(currentDate.timemillies())
        adapter.updateData(newTaskTodo)
    }
    private fun navigateToEditTodo() {
        adapter.onTodoClickListener = object : TaskAdapter.onClickTodoListener {
            override fun onclickitem(todo: Todo, index: Int) {
                val intent = Intent(requireActivity(),EditTaskActivity::class.java)
                intent.putExtra(Constants.TODO,todo)
                Log.e("${Constants.TODO}","${todo}")
                startActivity(intent)
            }


        }
    }
}