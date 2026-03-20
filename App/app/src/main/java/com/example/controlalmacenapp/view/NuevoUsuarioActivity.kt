package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.controlalmacenapp.R

class NuevoUsuarioActivity : AppCompatActivity() {

    private lateinit var ivFoto: ImageView
    private lateinit var etNombre: EditText
    private lateinit var cbAdmin: CheckBox
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnEditar: Button
    private lateinit var btnEliminar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)


        ivFoto = findViewById(R.id.ivFoto)
        etNombre = findViewById(R.id.etNombre)
        cbAdmin = findViewById(R.id.cbAdmin)
        etPassword = findViewById(R.id.etPassword)
        etEmail = findViewById(R.id.etEmail)
        btnEditar = findViewById(R.id.btnEditar)
        btnEliminar = findViewById(R.id.btnEliminar)

    }
}