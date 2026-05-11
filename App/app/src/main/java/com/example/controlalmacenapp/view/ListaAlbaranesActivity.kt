package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.AlbaranController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.AlbaranEntity
import com.example.controlalmacenapp.view.albaran.AlbaranAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListaAlbaranesActivity : BaseActivity() {

    private lateinit var controller: AlbaranController
    private lateinit var rvAlbaranes: RecyclerView
    private lateinit var adapter: AlbaranAdapter

    private var cifProveedorActual: String = ""
    private var nombreProveedorActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_albaranes)

        cifProveedorActual = intent.getStringExtra("PROVEEDOR_CIF") ?: ""
        nombreProveedorActual = intent.getStringExtra("PROVEEDOR_NOMBRE") ?: ""

        val db = AppDatabase.invoke(this)
        controller = AlbaranController(db.albaranDao())

        val tvTituloAlbaranes = findViewById<TextView>(R.id.tvTituloAlbaranes)

        if (cifProveedorActual.isBlank()) {
            tvTituloAlbaranes.text = "Gestión de Albaranes (Todos)"
        } else {
            tvTituloAlbaranes.text = "Albaranes: $nombreProveedorActual"
        }

        val btnNuevoAlbaran = findViewById<Button>(R.id.btnNuevoAlbaran)
        btnNuevoAlbaran.setOnClickListener {
            val intent = Intent(this, NuevoAlbaranActivity::class.java)
            if (cifProveedorActual.isNotBlank()) {
                intent.putExtra("PROVEEDOR_CIF", cifProveedorActual)
            }
            startActivity(intent)
        }

        val btnInforme = findViewById<Button>(R.id.btnInformeAlbaranes)
        btnInforme.setOnClickListener {
            val intent = Intent(this, InformeAlbaranesActivity::class.java)
            if (cifProveedorActual.isNotBlank()) {
                intent.putExtra("PROVEEDOR_CIF", cifProveedorActual)
                intent.putExtra("PROVEEDOR_NOMBRE", nombreProveedorActual)
            }
            startActivity(intent)
        }

        val btnVolver = findViewById<Button>(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        rvAlbaranes = findViewById(R.id.rvListaAlbaranes)
        rvAlbaranes.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        cargarAlbaranes()
    }

    private fun cargarAlbaranes() {
        lifecycleScope.launch(Dispatchers.IO) {

            val lista = if (cifProveedorActual.isNotBlank()) {
                controller.obtenerAlbaranesDeProveedor(cifProveedorActual)
            } else {
                controller.obtenerTodosLosAlbaranes()
            }

            withContext(Dispatchers.Main) {
                if (!::adapter.isInitialized) {
                    adapter = AlbaranAdapter(lista) { albaran ->

                        mostrarFichaAlbaran(albaran)

                    }
                    rvAlbaranes.adapter = adapter
                } else {
                    adapter.actualizarLista(lista)
                }
            }
        }
    }

    private fun mostrarFichaAlbaran(albaran: AlbaranEntity) {
        val estadoPago = if (albaran.pagado) "Sí (Pagado el: ${albaran.fechaPago})" else "Pendiente de pago"

        val mensajeFicha = """
            CIF del Proveedor: ${albaran.proveedorCif}
            Fecha de Creación: ${albaran.fechaEmision}
            Estado de Pago: $estadoPago
        """.trimIndent()

        AlertDialog.Builder(this@ListaAlbaranesActivity)
            .setTitle("Ficha del Albarán")
            .setMessage(mensajeFicha)
            .setIcon(android.R.drawable.ic_menu_info_details)
            .setPositiveButton("Cerrar", null)
            .show()
    }
}