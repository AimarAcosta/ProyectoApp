package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProductoController
import com.example.controlalmacenapp.model.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InformeInventarioActivity : BaseActivity() {

    private lateinit var controller: ProductoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe_inventario)

        val db = AppDatabase.invoke(this)
        controller = ProductoController(db.productoDao())

        findViewById<Button>(R.id.btnCerrarInformeInv).setOnClickListener { finish() }

        generarEstadisticas()
    }

    private fun generarEstadisticas() {
        lifecycleScope.launch(Dispatchers.IO) { val productos = controller.obtenerInventarioCompleto()

            val totalReferencias = productos.size
            var volumenFisico = 0
            var enAlerta = 0

            for (producto in productos) {
                if (producto.habilitado) {
                    volumenFisico += producto.cantidad
                    if (producto.cantidad <= producto.cantidadMinima) {
                        enAlerta++
                    }
                }
            }

            withContext(Dispatchers.Main) {
                findViewById<TextView>(R.id.tvTotalReferencias).text = totalReferencias.toString()
                findViewById<TextView>(R.id.tvVolumenTotal).text = volumenFisico.toString()
                findViewById<TextView>(R.id.tvAlertasStock).text = enAlerta.toString()
            }
        }
    }
}