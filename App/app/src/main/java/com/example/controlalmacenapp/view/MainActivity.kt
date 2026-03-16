package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.UsuarioController
import com.example.controlalmacenapp.model.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etUsuario = findViewById<EditText>(R.id.etUsuarioLogin)
        val etPassword = findViewById<EditText>(R.id.etPasswordLogin)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val usuarioTxt = etUsuario.text.toString().trim()
            val passwordTxt = etPassword.text.toString().trim()

            if (usuarioTxt.isEmpty() || passwordTxt.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch(Dispatchers.IO) {
                val database = AppDatabase.invoke(this@MainActivity)
                val controller = UsuarioController(database.usuarioDao())

                val credencialesValidas = controller.validarLogin(usuarioTxt, passwordTxt)

                withContext(Dispatchers.Main) {
                    if (credencialesValidas) {
                        Toast.makeText(this@MainActivity, "Acceso concedido", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@MainActivity, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}