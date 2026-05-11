package com.example.controlalmacenapp.view

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.AlbaranController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.AlbaranEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NuevoAlbaranActivity : BaseActivity() {

    private lateinit var controller: AlbaranController
    private var cifProveedorActual: String = ""
    private var rutaFotoAlbaran: String = ""

    private lateinit var ivFotoAlbaran: ImageView
    private lateinit var btnEscanearAlbaran: Button
    private lateinit var etCifProveedor: EditText
    private lateinit var etImporte: EditText
    private lateinit var cbPagado: CheckBox
    private lateinit var btnGuardarAlbaran: Button
    private lateinit var btnCancelarAlbaran: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_albaran)

        cifProveedorActual = intent.getStringExtra("PROVEEDOR_CIF") ?: ""

        val db = AppDatabase.invoke(this)
        controller = AlbaranController(db.albaranDao())

        ivFotoAlbaran = findViewById(R.id.ivFotoAlbaran)
        btnEscanearAlbaran = findViewById(R.id.btnEscanearAlbaran)
        etCifProveedor = findViewById(R.id.etCifProveedor)
        etImporte = findViewById(R.id.etImporte)
        cbPagado = findViewById(R.id.cbPagado)
        btnGuardarAlbaran = findViewById(R.id.btnGuardarAlbaran)
        btnCancelarAlbaran = findViewById(R.id.btnCancelarAlbaran)

        if (cifProveedorActual.isNotBlank()) {
            etCifProveedor.setText(cifProveedorActual)
            etCifProveedor.isEnabled = false
        }

        btnEscanearAlbaran.setOnClickListener {
            abrirCamara { uri ->
                if (uri != null) {
                    rutaFotoAlbaran = uri.toString()
                    ivFotoAlbaran.setImageURI(uri)
                } else {
                    Toast.makeText(this, "Captura cancelada", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnGuardarAlbaran.setOnClickListener { guardarAlbaran() }
        btnCancelarAlbaran.setOnClickListener { finish() }
    }

    private fun guardarAlbaran() {
        val cifFinal = etCifProveedor.text.toString().trim()
        val importeStr = etImporte.text.toString()

        if (cifFinal.isBlank()) {
            Toast.makeText(this, "Debe introducir el CIF del proveedor", Toast.LENGTH_SHORT).show()
            return
        }

        if (importeStr.isBlank()) {
            Toast.makeText(this, "Debe introducir un importe", Toast.LENGTH_SHORT).show()
            return
        }

        val importe = importeStr.toDoubleOrNull() ?: 0.0

        val nuevoAlbaran = AlbaranEntity(
            proveedorCif = cifFinal,
            importe = importe,
            pagado = cbPagado.isChecked,
            fechaPago = if (cbPagado.isChecked) System.currentTimeMillis() else null,
            fotoUri = rutaFotoAlbaran
        )

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                controller.guardarAlbaran(nuevoAlbaran)

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NuevoAlbaranActivity, "Albarán guardado correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NuevoAlbaranActivity, "Error: El CIF no pertenece a ningún proveedor registrado.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}