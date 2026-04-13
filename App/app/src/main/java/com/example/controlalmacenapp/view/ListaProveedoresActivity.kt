package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProveedorController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.view.proveedor.ProveedorAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaProveedoresActivity : BaseActivity() {

    private lateinit var controller: ProveedorController
    private lateinit var rvProveedores: RecyclerView
    private lateinit var adapter: ProveedorAdapter
    private lateinit var btnNuevoProveedor: Button
    private lateinit var btnVolver: Button // <-- Nueva variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_proveedores)

        val db = AppDatabase.invoke(this)
        controller = ProveedorController(db.proveedorDao())

        rvProveedores = findViewById(R.id.rvListaProveedores)
        rvProveedores.layoutManager = LinearLayoutManager(this)

        btnNuevoProveedor = findViewById(R.id.btnNuevoProveedor)
        btnVolver = findViewById(R.id.btnVolver) // <-- Enlazamos el botón

        btnVolver.setOnClickListener {
            finish()
        }

        btnNuevoProveedor.setOnClickListener {
            startActivity(Intent(this, NuevoProveedorActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        cargarProveedores()
    }

    private fun cargarProveedores() {
        lifecycleScope.launch(Dispatchers.IO) {
            val lista = controller.obtenerProveedoresActivos()

            withContext(Dispatchers.Main) {
                if (!::adapter.isInitialized) {
                    adapter = ProveedorAdapter(
                        proveedores = lista,
                        onItemClick = { proveedor ->
                            val intent = Intent(this@ListaProveedoresActivity, ListaAlbaranesActivity::class.java)
                            intent.putExtra("PROVEEDOR_CIF", proveedor.cif)
                            intent.putExtra("PROVEEDOR_NOMBRE", proveedor.nombre)
                            startActivity(intent)
                        }
                    )
                    rvProveedores.adapter = adapter
                } else {
                    adapter.actualizarLista(lista)
                }
            }
        }
    }
}