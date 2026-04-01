package com.example.controlalmacenapp.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.UsuarioEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NuevoUsuarioActivity : BaseActivity() {

    private lateinit var ivFoto: ImageView

    private lateinit var btnTomarFoto: Button
    private lateinit var etNombre: EditText
    private lateinit var cbAdmin: CheckBox
    private lateinit var cbHabilitado: CheckBox
    private lateinit var etPassword: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var btnEliminar: Button

    private var nombreUsuarioEditar: String? = null
    private var usuarioIdActual: Int = 0

    private var fotoRutaActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_usuario)

        ivFoto = findViewById(R.id.ivFoto)
        btnTomarFoto = findViewById(R.id.btnTomarFoto)
        etNombre = findViewById(R.id.etNombre)
        cbAdmin = findViewById(R.id.cbAdmin)
        cbHabilitado = findViewById(R.id.cbHabilitado)
        etPassword = findViewById(R.id.etPassword)
        etEmail = findViewById(R.id.etEmail)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)
        btnEliminar = findViewById(R.id.btnEliminar)

        val db = AppDatabase.invoke(this)
        val usuarioDao = db.usuarioDao()

        nombreUsuarioEditar = intent.getStringExtra("NOMBRE_USUARIO_EDITAR")

        if (nombreUsuarioEditar != null) {
            btnGuardar.text = "Actualizar"
            btnEliminar.visibility = View.VISIBLE
            cbHabilitado.visibility = View.VISIBLE

            lifecycleScope.launch(Dispatchers.IO) {
                val user = usuarioDao.obtenerUsuarioPorNombre(nombreUsuarioEditar!!)
                user?.let {
                    usuarioIdActual = it.id
                    withContext(Dispatchers.Main) {
                        etNombre.setText(it.nombre)
                        etPassword.setText(it.password)
                        etEmail.setText(it.email)
                        cbAdmin.isChecked = it.esAdministrador
                        cbHabilitado.isChecked = it.habilitado
                    }
                }
            }
        } else {
            btnGuardar.text = "Guardar"
            btnEliminar.visibility = View.GONE
            cbHabilitado.visibility = View.GONE
            cbHabilitado.isChecked = true
        }

        btnTomarFoto.setOnClickListener {
            abrirCamara { uri ->
                if (uri != null) {
                    fotoRutaActual = uri.toString()
                    ivFoto.setImageURI(uri)
                } else {
                    Toast.makeText(this, "Foto cancelada", Toast.LENGTH_SHORT).show()
                }
            }
        }


        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val password = etPassword.text.toString()

            if (nombre.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Nombre y contraseña obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = UsuarioEntity(
                id = usuarioIdActual,
                nombre = nombre,
                password = password,
                email = etEmail.text.toString(),
                esAdministrador = cbAdmin.isChecked,
                habilitado = cbHabilitado.isChecked,
                imagenUrl = fotoRutaActual
            )

            lifecycleScope.launch(Dispatchers.IO) {
                if (nombreUsuarioEditar != null) {
                    usuarioDao.updateUsuario(user)
                } else {
                    usuarioDao.insertUsuario(user)
                }
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NuevoUsuarioActivity, "Operación completada", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

        btnEliminar.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Eliminar Perfil")
                .setMessage("¿Seguro que quieres eliminar este perfil de forma permanente?")
                .setPositiveButton("ELIMINAR") { _, _ ->
                    lifecycleScope.launch(Dispatchers.IO) {
                        val userToDelete = usuarioDao.obtenerUsuarioPorNombre(nombreUsuarioEditar!!)
                        userToDelete?.let { usuarioDao.deleteUsuario(it) }
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@NuevoUsuarioActivity, "Perfil eliminado", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        btnCancelar.setOnClickListener { finish() }
    }
}