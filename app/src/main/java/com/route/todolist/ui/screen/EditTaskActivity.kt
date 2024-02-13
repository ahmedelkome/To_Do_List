package com.route.todolist.ui.screen

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.route.todolist.CalenderExt.timemillies
import com.route.todolist.Constant.Constants
import com.route.todolist.Todo
import com.route.todolist.database.MyDataBase
import com.route.todolist.database.TodoDao
import com.route.todolist.databinding.ActivityEditTaskBinding
import com.route.todolist.ui.fragments.TaskFragment
import java.util.Calendar

class EditTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditTaskBinding
    var currentEditDate = Calendar.getInstance()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val todo = intent.getSerializableExtra(Constants.TODO,Todo::class.java)!!
        binding.taskEditTie.setText(todo?.title)
        binding.detailsEditTie.setText(todo?.description)
        currentEditDate.timeInMillis = todo?.date!!
        setTimeEdit()
        saveChanges()
        backButton()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
   private fun saveChanges() {
        binding.saveChanges.setOnClickListener {
        val todo = intent.getSerializableExtra(Constants.TODO,Todo::class.java)!!
            todo?.title = binding.taskEditTie.text.toString()
            todo?.description = binding.detailsEditTie.text.toString()
            todo?.date = currentEditDate.timeInMillis
            todo?.isDone = todo?.isDone
            MyDataBase.getInstance(this).getTodoDao().updateTodo(todo!!)
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    private fun setTimeEdit() {
        binding.editTime.text = "${currentEditDate.get(Calendar.MONTH) + 1} " +
                "/ ${currentEditDate.get(Calendar.DAY_OF_MONTH)} " +
                "/ ${currentEditDate.get(Calendar.YEAR)}"
        binding.editTime.setOnClickListener {
            var dateEditPicker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    currentEditDate.set(Calendar.YEAR, year)
                    currentEditDate.set(Calendar.MONTH, month)
                    currentEditDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    binding.editTime.text = "${currentEditDate.get(Calendar.MONTH) + 1} " +
                            "/ ${currentEditDate.get(Calendar.DAY_OF_MONTH)} " +
                            "/ ${currentEditDate.get(Calendar.YEAR)}"
                }, currentEditDate.get(Calendar.YEAR),
                currentEditDate.get(Calendar.MONTH),
                currentEditDate.get(Calendar.DAY_OF_MONTH)
            )
            dateEditPicker.datePicker.minDate = Calendar.getInstance().timeInMillis
            dateEditPicker.show()
        }
    }


    private fun backButton() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}