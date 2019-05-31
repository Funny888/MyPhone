package com.example.myphone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.Toolbar
import android.view.*

class MainActivity : AppCompatActivity() {
    lateinit var bottomNav:BottomNavigationView
    lateinit var tool:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.navigation)
        tool = findViewById(R.id.myToolbar2)
        setSupportActionBar(tool)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.search_contacts,menu)
        return super.onCreateOptionsMenu(menu)
    }
}
