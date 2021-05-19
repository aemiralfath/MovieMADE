package com.aemiralfath.moviemade.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.aemiralfath.moviemade.MainActivity
import com.aemiralfath.moviemade.R

class SplashScreenActivity : AppCompatActivity() {
    companion object {
        const val TIME = 3000L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, TIME)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}