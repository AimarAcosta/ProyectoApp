package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.AlbaranController
import com.example.controlalmacenapp.model.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class InformeAlbaranesActivity : BaseActivity() {

    private lateinit var controller: AlbaranController
    private var cifProveedorActual: String = ""
    private var nombreProveedorActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informe_albaranes)

        cifProveedorActual = intent.getStringExtra("PROVEEDOR_CIF") ?: ""
        nombreProveedorActual = intent.getStringExtra("PROVEEDOR_NOMBRE") ?: ""

        val db = AppDatabase.invoke(this)
        controller = AlbaranController(db.albaranDao())

        findViewById<Button>(R.id.btnCerrarInforme).setOnClickListener { finish() }

        generarInforme()
    }

    private fun generarInforme() {
        lifecycleScope.launch(Dispatchers.IO) {
            val listaAlbaranes = if (cifProveedorActual.isNotBlank()) {
                controller.obtenerAlbaranesDeProveedor(cifProveedorActual)
            } else {
                controller.obtenerTodosLosAlbaranes()
            }

            var totalImporte = 0.0
            var pagados = 0
            var pendientes = 0

            for (albaran in listaAlbaranes) {
                totalImporte += albaran.importe
                if (albaran.pagado) {
                    pagados++
                } else {
                    pendientes++
                }
            }

            withContext(Dispatchers.Main) {
                val tvResumenProveedores = findViewById<TextView>(R.id.tvResumenProveedores)
                val tvTotalImporte = findViewById<TextView>(R.id.tvTotalImporte)
                val tvPagados = findViewById<TextView>(R.id.tvPagados)
                val tvPendientes = findViewById<TextView>(R.id.tvPendientes)

                if (cifProveedorActual.isNotBlank()) {
                    tvResumenProveedores.text = "Proveedor:\n$nombreProveedorActual (CIF: $cifProveedorActual)"
                } else {
                    tvResumenProveedores.text = "Informe Global\nTodos los proveedores"
                }

                tvTotalImporte.text = String.format("Importe Total: %.2f €", totalImporte)
                tvPagados.text = "Albaranes Pagados: $pagados"
                tvPendientes.text = "Albaranes Pendientes: $pendientes"
            }
        }
    }
}