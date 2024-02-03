package com.route.todolist.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.route.todolist.R
import com.route.todolist.databinding.ActivityHomeBinding
import com.route.todolist.ui.fragments.AddTaskFragment
import com.route.todolist.ui.fragments.SettingFragment
import com.route.todolist.ui.fragments.TaskFragment

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        pushFragment(TaskFragment())
        selectIconBottomNavi()
        floatActionAdd()
    }

    private fun floatActionAdd() {
        binding.fabAddTask.setOnClickListener {
            var addTask = AddTaskFragment()
            addTask.show(supportFragmentManager,"")
        }
    }

    private fun selectIconBottomNavi(){
        binding.bottomNav.setOnItemSelectedListener {menuItem->
            if (menuItem.itemId==R.id.task){
                pushFragment(TaskFragment())
            }
            else if (menuItem.itemId==R.id.setting){
                pushFragment(SettingFragment())
            }
            return@setOnItemSelectedListener true
        }
    }
    private fun pushFragment(fragment:Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack("")
            .commit()
    }
}