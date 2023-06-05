package com.santiagotorres.pokeapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.santiagotorres.pokeapp.databinding.ActivitySplashBinding
import com.santiagotorres.pokeapp.main.MainActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)


        val timer = Timer()
        timer.schedule(timerTask {
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000
        )
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "OK")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "OK")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "OK")
    }

    override fun onStop() {
        super.onStop()
        Log.d("onStop", "OK")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "OK")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "OK")
    }
}
