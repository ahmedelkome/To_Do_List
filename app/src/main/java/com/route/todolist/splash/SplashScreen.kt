package com.route.todolist.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.route.todolist.R
import com.route.todolist.screens.HomeActivity

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startHomeActivity()
    }

    private fun startHomeActivity() {
        Handler(mainLooper).postDelayed({
         startActivity(Intent(this,HomeActivity::class.java))
         finish()
        },3000)
    }
}