package com.route.todolist.ui.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.core.widget.addTextChangedListener
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.route.todolist.R
import com.route.todolist.databinding.FragmentAddTaskBinding
import java.util.Calendar

class AddTaskFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentAddTaskBinding

    var selectedDate = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.date.text = "${selectedDate.get(Calendar.MONTH) + 1} " +
                "/ ${selectedDate.get(Calendar.DAY_OF_MONTH)} " +
                "/ ${selectedDate.get(Calendar.YEAR)}"
        intiallistener()
    }

    private fun intiallistener()
    {
        binding.addtask.setOnClickListener {
          valitadeTask()
        }
        binding.tasktextinput.editText!!.addTextChangedListener {
            valitadeTask()
        }
        binding.descriptiontextinput.editText!!.addTextChangedListener {
            valitadeTask()
        }

        binding.date.setOnClickListener{
            val datepicker = DatePickerDialog(requireContext(),
                { _, year, month, dayOfMonth ->
                    selectedDate.set(Calendar.YEAR,year)
                    selectedDate.set(Calendar.MONTH,month)
                    selectedDate.set(Calendar.DAY_OF_MONTH,dayOfMonth)

                    binding.date.text = "${selectedDate.get(Calendar.MONTH) + 1} " +
                            "/ ${selectedDate.get(Calendar.DAY_OF_MONTH)} " +
                            "/ ${selectedDate.get(Calendar.YEAR)}"
                }
                ,selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH))
            datepicker.show()
        }

    }
    private fun valitadeTask():Boolean {
        var isValid = true
        val title = binding.tasktextinput.editText!!.text.toString()
        val description = binding.descriptiontextinput.editText!!.text.toString()
        if (title.isEmpty()) {
            binding.tasktextinput.error = "please enter title"
            isValid = false
        } else {
            binding.tasktextinput.error = null
        }
        if (description.isEmpty()) {
            binding.descriptiontextinput.error = "please enter description"
            isValid = false
        } else {
            binding.descriptiontextinput.error = null
        }
        return isValid

    }


}