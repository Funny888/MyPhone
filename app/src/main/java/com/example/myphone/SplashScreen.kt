package com.example.myphone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class SplashScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.SplashScreen)
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}