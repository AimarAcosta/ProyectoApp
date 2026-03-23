package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.UsuarioController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.view.usuario.UsuarioAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaUsuariosActivity : BaseActivity() {

    private lateinit var rvListaUsuarios: RecyclerView
    private lateinit var controller: UsuarioController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_usuarios)

        val database = AppDatabase.invoke(this)
        controller = UsuarioController(database.usuarioDao())

        rvListaUsuarios = findViewById(R.id.rvListaUsuarios)
        rvListaUsuarios.layoutManager = LinearLayoutManager(this)

        val btnIrCrearUsuario = findViewById<Button>(R.id.btnIrCrearUsuario)
        btnIrCrearUsuario.setOnClickListener {
            startActivity(Intent(this, NuevoUsuarioActivity::class.java))
        }

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        cargarDirectorio()
    }

    private fun cargarDirectorio() {
        lifecycleScope.launch(Dispatchers.IO) {
            val lista = controller.obtenerDirectorioAdmin()

            withContext(Dispatchers.Main) {
                rvListaUsuarios.adapter = UsuarioAdapter(lista) { usuarioSeleccionado ->
                    val intent = Intent(this@ListaUsuariosActivity, NuevoUsuarioActivity::class.java)
                    intent.putExtra("NOMBRE_USUARIO_EDITAR", usuarioSeleccionado.nombre)
                    startActivity(intent)
                }
            }
        }
    }
}