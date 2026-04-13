package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProveedorController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.ProveedorEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NuevoProveedorActivity : BaseActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCif: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    private lateinit var controller: ProveedorController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_proveedor)

        val db = AppDatabase.invoke(this)
        controller = ProveedorController(db.proveedorDao())

        etNombre = findViewById(R.id.etNombreProveedor)
        etCif = findViewById(R.id.etCifProveedor)
        etTelefono = findViewById(R.id.etTelefonoProveedor)
        etEmail = findViewById(R.id.etEmailProveedor)
        btnGuardar = findViewById(R.id.btnGuardarProveedor)
        btnCancelar = findViewById(R.id.btnCancelarProveedor)

        btnGuardar.setOnClickListener { guardarProveedor() }
        btnCancelar.setOnClickListener { finish() }
    }

    private fun guardarProveedor() {
        val nombreStr = etNombre.text.toString()
        val cifStr = etCif.text.toString()

        if (nombreStr.isBlank() || cifStr.isBlank()) {
            Toast.makeText(this, "El nombre y el CIF son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevoProveedor = ProveedorEntity(
            nombre = nombreStr,
            cif = cifStr,
            telefono = etTelefono.text.toString(),
            email = etEmail.text.toString(),
            habilitado = true
        )

        lifecycleScope.launch(Dispatchers.IO) {
            controller.guardarProveedor(nuevoProveedor)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@NuevoProveedorActivity, "Proveedor registrado", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}