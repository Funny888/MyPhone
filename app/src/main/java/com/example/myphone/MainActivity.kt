package com.example.myphone

import com.example.myphone.fragments.BlackWhiteList
import com.example.myphone.fragments.ContactList
import com.example.myphone.fragments.FavoriteList
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    lateinit var bottomNav: BottomNavigationView
    lateinit var tool: Toolbar
    lateinit var contact: ContactList
    lateinit var favorite: FavoriteList
    lateinit var blackwhite: BlackWhiteList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemSelectedListener(this)
        tool = findViewById(R.id.myToolbar2)
        setSupportActionBar(tool)
        favorite = FavoriteList().instance()
        contact = ContactList().instance()
        blackwhite = BlackWhiteList().instance()
        supportFragmentManager.beginTransaction().add(R.id.frame, contact).commitNow()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_contacts, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        var frTranz: FragmentTransaction = supportFragmentManager.beginTransaction()


        when (p0.itemId) {
            R.id.contact_item -> {
                frTranz.replace(R.id.frame, contact)
            }
            R.id.favorite_item -> {
                frTranz.replace(R.id.frame, favorite)
            }
            R.id.black_white_list_item -> {
                frTranz.replace(R.id.frame, blackwhite)
            }

        }
        frTranz.commit()
        return true
    }
}
