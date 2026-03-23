package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private val timeoutHandler = Handler(Looper.getMainLooper())
    private val timeoutRunnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private val TIEMPO_INACTIVIDAD = 600000L

    override fun onResume() {
        super.onResume()
        reiniciarTemporizador()
    }

    override fun onPause() {
        super.onPause()
        detenerTemporizador()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        reiniciarTemporizador()
    }

    private fun reiniciarTemporizador() {
        detenerTemporizador()
        timeoutHandler.postDelayed(timeoutRunnable, TIEMPO_INACTIVIDAD)
    }

    private fun detenerTemporizador() {
        timeoutHandler.removeCallbacks(timeoutRunnable)
    }
}