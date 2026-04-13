package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProductoController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.view.producto.ProductoAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MenuPrincipalActivity : BaseActivity() {

    private lateinit var controller: ProductoController
    private lateinit var rvListaProductos: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private var esAdmin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val db = AppDatabase.invoke(this)
        controller = ProductoController(db.productoDao())

        esAdmin = intent.getBooleanExtra("ES_ADMIN", false)
        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO") ?: "Usuario"

        val tvBienvenida = findViewById<TextView>(R.id.tvBienvenida)
        tvBienvenida.text = "Panel: $nombreUsuario"

        val llPanelAdmin = findViewById<LinearLayout>(R.id.llPanelAdmin)
        val btnGestionUsuarios = findViewById<Button>(R.id.btnGestionUsuarios)
        val btnNuevoProducto = findViewById<Button>(R.id.btnNuevoProducto)
        val btnGestionProveedores = findViewById<Button>(R.id.btnGestionProveedores)

        if (esAdmin) {
            llPanelAdmin.visibility = View.VISIBLE
            btnGestionUsuarios.setOnClickListener {
                startActivity(Intent(this, ListaUsuariosActivity::class.java))
            }
            btnNuevoProducto.setOnClickListener {
                startActivity(Intent(this, NuevoProductoActivity::class.java))
            }
            btnGestionProveedores.setOnClickListener {
                startActivity(Intent(this, ListaProveedoresActivity::class.java))
            }
        } else {
            llPanelAdmin.visibility = View.GONE
        }

        findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        rvListaProductos = findViewById(R.id.rvListaProductos)
        rvListaProductos.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        cargarInventario()
    }

    private fun cargarInventario() {
        lifecycleScope.launch(Dispatchers.IO) {
            val productos = controller.obtenerInventarioCompleto()

            withContext(Dispatchers.Main) {
                if (!::adapter.isInitialized) {
                    adapter = ProductoAdapter(
                        productos = productos,
                        onSumarClick = { producto ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                controller.sumarStock(producto.id)
                                cargarInventario()
                            }
                        },
                        onRestarClick = { producto ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                val exito = controller.restarStock(producto)
                                withContext(Dispatchers.Main) {
                                    if (!exito) Toast.makeText(this@MenuPrincipalActivity, "El stock ya es 0", Toast.LENGTH_SHORT).show()
                                    cargarInventario()
                                }
                            }
                        },
                        onSumarCincoClick = { producto ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                val nuevoStock = producto.cantidad + 5
                                controller.guardarProducto(producto.copy(cantidad = nuevoStock))
                                cargarInventario()
                            }
                        },
                        onRestarCincoClick = { producto ->
                            lifecycleScope.launch(Dispatchers.IO) {
                                if (producto.cantidad > 0) {
                                    // Blindaje de seguridad: nunca bajar de 0
                                    val nuevoStock = if (producto.cantidad - 5 < 0) 0 else producto.cantidad - 5
                                    controller.guardarProducto(producto.copy(cantidad = nuevoStock))
                                    cargarInventario()
                                } else {
                                    withContext(Dispatchers.Main) {
                                        Toast.makeText(this@MenuPrincipalActivity, "El stock ya es 0", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        },
                        onItemClick = { producto ->
                            if (esAdmin) {
                                val intent = Intent(this@MenuPrincipalActivity, NuevoProductoActivity::class.java)
                                intent.putExtra("PRODUCTO_ID", producto.id)
                                startActivity(intent)
                            }
                        }
                    )
                    rvListaProductos.adapter = adapter
                } else {
                    adapter.actualizarLista(productos)
                }
            }
        }
    }
}