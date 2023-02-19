package com.example.deber1_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTaskList = findViewById<android.widget.Button>(R.id.btn_tasker)
        btnTaskList.setOnClickListener {
            val intent = Intent(this, SettingsList::class.java)
            startActivity(intent)
        }

        val bottomNavigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.fragment_home

        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.fragment_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.fragment_task_list -> {
                    val intent = Intent(this, TaskList::class.java)
                    startActivity(intent)
                    true
                }
                R.id.fragment_settings -> {
                    val intent = Intent(this, SettingsList::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}