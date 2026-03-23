package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.controlalmacenapp.R

class MenuPrincipalActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val esAdmin = intent.getBooleanExtra("ES_ADMIN", false)
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO") ?: "Usuario"

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        tvBienvenida.text = "Panel de Control - $nombreUsuario"

        val btnGestionUsuarios = findViewById<Button>(R.id.btnGestionUsuarios)

        if (esAdmin) {
            btnGestionUsuarios.visibility = View.VISIBLE
            btnGestionUsuarios.setOnClickListener {
                val intent = Intent(this, ListaUsuariosActivity::class.java)
                startActivity(intent)
            }
        } else {
            btnGestionUsuarios.visibility = View.GONE
        }

        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}