package com.example.controlalmacenapp.view

import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.AlbaranController
import com.example.controlalmacenapp.model.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetalleAlbaranActivity : BaseActivity() {

    private lateinit var controller: AlbaranController
    private var albaranId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_albaran)

        albaranId = intent.getIntExtra("ALBARAN_ID", -1)

        if (albaranId == -1) {
            Toast.makeText(this, "Error al cargar el documento", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val db = AppDatabase.invoke(this)
        controller = AlbaranController(db.albaranDao())

        val btnCerrar = findViewById<Button>(R.id.btnCerrarVisor)
        btnCerrar.setOnClickListener { finish() }

        cargarDatosAlbaran()
    }

    private fun cargarDatosAlbaran() {
        lifecycleScope.launch(Dispatchers.IO) {
            val albaran = controller.obtenerAlbaran(albaranId)

            withContext(Dispatchers.Main) {
                if (albaran != null) {
                    val tvInfo = findViewById<TextView>(R.id.tvInfoAlbaran)
                    val ivFoto = findViewById<ImageView>(R.id.ivVisorFoto)

                    val estadoPago = if (albaran.pagado) "PAGADO" else "PENDIENTE"
                    tvInfo.text = "Importe: ${String.format("%.2f", albaran.importe)} € | Estado: $estadoPago"

                    if (!albaran.fotoUri.isNullOrBlank()) {
                        ivFoto.setImageURI(Uri.parse(albaran.fotoUri))
                    } else {
                        Toast.makeText(this@DetalleAlbaranActivity, "Este albarán no tiene foto adjunta", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@DetalleAlbaranActivity, "Albarán no encontrado", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}