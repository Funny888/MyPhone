package com.example.myphone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup.LayoutParams
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_splash_screen)
        var text: TextView = TextView(this)
        var param: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        text.setText(getString(R.string.splash_screen))
        this.addContentView(text, param)
        var anim: Animation
        anim = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        text.startAnimation(anim)
        text.postDelayed(Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(R.anim.start_activity,R.anim.stop_activity)
        }, 7000)

    }
}