package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.UsuarioController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.UsuarioEntity
import com.example.controlalmacenapp.view.usuario.UsuarioAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var controller: UsuarioController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AppDatabase.invoke(this)
        controller = UsuarioController(database.usuarioDao())

        val rvUsuarios = findViewById<RecyclerView>(R.id.rvUsuarios)
        rvUsuarios.layoutManager = GridLayoutManager(this, 5)

        cargarUsuarios(rvUsuarios)



    }

    private fun cargarUsuarios(rv: RecyclerView) {
        lifecycleScope.launch(Dispatchers.IO) {
            val listaUsuarios = controller.obtenerTodosLosUsuarios()

            withContext(Dispatchers.Main) {
                if (listaUsuarios.isEmpty()) {
                    Toast.makeText(this@MainActivity, "Base de datos vacía", Toast.LENGTH_SHORT).show()
                } else {
                    rv.adapter = UsuarioAdapter(listaUsuarios) { usuarioSeleccionado ->

                        if (usuarioSeleccionado.nombre.uppercase() == "ADMIN") {
                            mostrarDialogoPassword(usuarioSeleccionado)
                        } else {
                            accederDirectamente(usuarioSeleccionado)
                        }

                    }
                }
            }
        }
    }
    private fun accederDirectamente(usuario: UsuarioEntity) {
        Toast.makeText(this, "¡Bienvenido, ${usuario.nombre}!", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MenuPrincipalActivity::class.java)
        intent.putExtra("ES_ADMIN", usuario.esAdministrador)
        intent.putExtra("NOMBRE_USUARIO", usuario.nombre)

        startActivity(intent)
        finish()
    }

    private fun mostrarDialogoPassword(usuario: UsuarioEntity) {
        val input = EditText(this)
        input.inputType = android.text.InputType.TYPE_CLASS_TEXT or android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        input.hint = "Escribe tu clave"

        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Accediendo como ${usuario.nombre}")
            .setMessage("Por favor, introduce tu contraseña:")
            .setView(input)
            .setPositiveButton("ACCEDER") { _, _ ->
                val passwordEscrita = input.text.toString()
                validarCredenciales(usuario, passwordEscrita)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun validarCredenciales(usuario: UsuarioEntity, pass: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val accesoPermitido = controller.validarLogin(usuario.nombre, pass)

            withContext(Dispatchers.Main) {
                if (accesoPermitido) {
                    Toast.makeText(this@MainActivity, "¡Bienvenido, ${usuario.nombre}!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity, MenuPrincipalActivity::class.java)
                    intent.putExtra("ES_ADMIN", usuario.esAdministrador)
                    intent.putExtra("NOMBRE_USUARIO", usuario.nombre)

                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}