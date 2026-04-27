package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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
        nombreProveedorActual = intent.getStringExtra("PROVEEDOR_NOMBRE") ?: "Varios"

        if (cifProveedorActual.isBlank()) {
            Toast.makeText(this, "Error de datos", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val db = AppDatabase.invoke(this)
        controller = AlbaranController(db.albaranDao())

        findViewById<Button>(R.id.btnCerrarInforme).setOnClickListener { finish() }

        generarInforme()
    }

    private fun generarInforme() {
        lifecycleScope.launch(Dispatchers.IO) {
            // Extraemos los albaranes como pruebas
            val listaAlbaranes = controller.obtenerAlbaranesDeProveedor(cifProveedorActual)

            // Procesamos los datos
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

                tvResumenProveedores.text = "Proveedor:\n$nombreProveedorActual (CIF: $cifProveedorActual)"
                tvTotalImporte.text = String.format("Importe Total: %.2f €", totalImporte)
                tvPagados.text = "Albaranes Pagados: $pagados"
                tvPendientes.text = "Albaranes Pendientes: $pendientes"
            }
        }
    }
}