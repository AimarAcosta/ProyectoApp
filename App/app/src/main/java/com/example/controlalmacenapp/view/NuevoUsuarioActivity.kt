package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.UsuarioEntity
import kotlinx.coroutines.launch

class NuevoUsuarioActivity : AppCompatActivity() {

    private lateinit var ivFoto: ImageView
    private lateinit var etNombre: EditText
    private lateinit var cbAdmin: CheckBox
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        ivFoto = findViewById(R.id.ivFoto)
        etNombre = findViewById(R.id.etNombre)
        cbAdmin = findViewById(R.id.cbAdmin)
        etPassword = findViewById(R.id.etPassword)
        etEmail = findViewById(R.id.etEmail)
        btnGuardar = findViewById(R.id.btnGuardar)

        val db = AppDatabase(this)
        val usuarioDao = db.usuarioDao()

        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val password = etPassword.text.toString()
            val email = etEmail.text.toString()
            val admin = cbAdmin.isChecked

            val user = UsuarioEntity(
                nombre = nombre,
                password = password,
                email = email,
                esAdministrador = admin,
                habilitado = true,
                imagenUrl = ""
            )

            lifecycleScope.launch {
                usuarioDao.insertUsuario(user)
            }
            startActivity(Intent(this@NuevoUsuarioActivity, MainActivity::class.java))
            finish()
        }
    }
}