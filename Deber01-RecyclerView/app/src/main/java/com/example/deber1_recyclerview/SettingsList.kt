package com.example.deber1_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class SettingsList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings_list)

        val settingsList = ArrayList<Setting>()
        settingsList.add(
            Setting("Show profile photo to business",
                "Business users can see your profile photo"))
        settingsList.add(
            Setting("Change pasword",
                "Change your password"))
        settingsList.add(
            Setting("Delete profile",
                "Delete your profile"))
        settingsList.add(
            Setting("Privacy",
                "Change your privacy settings"))
        settingsList.add(
            Setting("Intro & Instructions",
                "For example, how tasks are assigned"))
        settingsList.add(
            Setting("For support",
                "help@taskerplatform.com"))
        settingsList.add(
            Setting("App version",
                "2.0.9"))

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_settings)
        inicializarRecyclerView(settingsList, recyclerView)

        val bottomNavigation = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.selectedItemId = R.id.fragment_settings

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
        settingsList: ArrayList<Setting>,
        recyclerView: RecyclerView
    ) {
        val adaptadorSetting = AdaptadorSetting(settingsList)
        recyclerView.adapter = adaptadorSetting
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptadorSetting.notifyDataSetChanged()
    }

}