package com.example.deber1_recyclerview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class TaskList : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_list)

        // Definir la lista
        val taskList = ArrayList<Task>()
        taskList.add(
            Task("Mechanical Engineering",
                "Industrialize a thin-film deposition on gold electrode for med-tech equipment",
                "10K",
                "$"))
        taskList.add(
            Task(
                "Heavy Engineering",
                "Design and development of heavy on-dock equipment",
                "20K",
                "$"))
        taskList.add(
            Task("Consumer Electronics",
                "Designed and develop an NFC enabled, battery for all us",
                "30K",
                "$"))
        taskList.add(
            Task("Mechanical Engineering",
                "Industrialize a thin-film deposition on gold electrode for med-tech equipment",
                "10K",
                "$"))
        taskList.add(
            Task(
                "Heavy Engineering",
                "Design and development of heavy on-dock equipment",
                "20K",
                "$"))
        taskList.add(
            Task("Consumer Electronics",
                "Designed and develop an NFC enabled, battery for all us",
                "30K",
                "$"))

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tasks)
        inicializarRecyclerView(taskList, recyclerView)

        val bottomNavigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.fragment_task_list

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

    fun inicializarRecyclerView (
        taskList: ArrayList<Task>,
        recyclerView: RecyclerView
    ) {
        val adaptadorTask = AdaptadorTask(taskList)
        recyclerView.adapter = adaptadorTask
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptadorTask.notifyDataSetChanged()
    }

}