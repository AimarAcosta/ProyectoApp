package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProductoController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.ProductoEntity
import com.example.controlalmacenapp.view.inventario.InventarioExcelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InventarioExcelActivity : BaseActivity() {

    private lateinit var controller: ProductoController
    private lateinit var rvInventario: RecyclerView
    private lateinit var adapter: InventarioExcelAdapter
    private var listaCompleta: List<ProductoEntity> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventario_excel)

        val db = AppDatabase.invoke(this)
        controller = ProductoController(db.productoDao())

        rvInventario = findViewById(R.id.rvInventarioExcel)
        rvInventario.layoutManager = LinearLayoutManager(this)

        findViewById<Button>(R.id.btnVolverInventario).setOnClickListener { finish() }

        configurarFiltros()
        cargarDatosExcel()
    }

    private fun cargarDatosExcel() {
        lifecycleScope.launch(Dispatchers.IO) {
            listaCompleta = controller.obtenerInventarioCompleto()

            withContext(Dispatchers.Main) {
                adapter = InventarioExcelAdapter(listaCompleta)
                rvInventario.adapter = adapter
            }
        }
    }

    private fun configurarFiltros() {
        val btnTodos = findViewById<Button>(R.id.btnFiltroTodos)
        val btnMinimo = findViewById<Button>(R.id.btnFiltroMinimo)
        val btnDeshabilitados = findViewById<Button>(R.id.btnFiltroDeshabilitados)

        btnTodos.setOnClickListener {
            adapter.actualizarLista(listaCompleta)
        }

        btnMinimo.setOnClickListener {
            val listaFiltrada = listaCompleta.filter { it.habilitado && it.cantidad <= it.cantidadMinima }
            adapter.actualizarLista(listaFiltrada)
        }

        btnDeshabilitados.setOnClickListener {
            val listaFiltrada = listaCompleta.filter { !it.habilitado }
            adapter.actualizarLista(listaFiltrada)
        }
    }
}