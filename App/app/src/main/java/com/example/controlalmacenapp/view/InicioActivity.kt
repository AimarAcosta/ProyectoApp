package com.example.controlalmacenapp.view

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.UsuarioController
import com.example.controlalmacenapp.model.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InicioActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val logo = findViewById<ImageView>(R.id.logoImage)

        val rotate = ObjectAnimator.ofFloat(logo, "rotation", 0f, 360f)
        rotate.duration = 2000
        rotate.repeatCount = ObjectAnimator.INFINITE
        rotate.interpolator = AccelerateDecelerateInterpolator()
        rotate.start()

        lifecycleScope.launch(Dispatchers.IO) {

            val database = AppDatabase.invoke(this@InicioActivity)
            val controller = UsuarioController(database.usuarioDao())
            controller.inicializarDatosSiVacio()

            delay(3000)

            withContext(Dispatchers.Main) {
                startActivity(Intent(this@InicioActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}