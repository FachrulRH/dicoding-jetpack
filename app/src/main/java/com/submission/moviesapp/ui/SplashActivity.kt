package com.submission.moviesapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.submission.moviesapp.R

class SplashActivity : AppCompatActivity() {

    private val time: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler(mainLooper)
        handler.postDelayed({
            val intentMain = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intentMain)
            finish()
        }, time)

    }
}