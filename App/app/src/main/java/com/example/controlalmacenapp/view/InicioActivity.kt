package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.example.controlalmacenapp.R
import android.widget.ImageView

//En esta clase se realiza la animacion inicial de la app con el logo girando.
class InicioActivity: AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_inicio)

            val logo = findViewById<ImageView>(R.id.logoImage)


            val rotate = RotateAnimation(
                0f,
                360f,
                Animation.RELATIVE_TO_SELF,
                0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f
            )

            rotate.duration = 2000
            rotate.repeatCount = Animation.INFINITE

            logo.startAnimation(rotate)


            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, 3000)
        }
    }
