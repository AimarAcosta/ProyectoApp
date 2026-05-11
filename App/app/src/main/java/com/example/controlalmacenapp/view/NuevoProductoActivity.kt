package com.example.controlalmacenapp.view

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProductoController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.ProductoEntity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NuevoProductoActivity : BaseActivity() {

    private lateinit var ivPreviewFoto: ImageView
    private lateinit var btnTomarFoto: Button
    private lateinit var etNombre: EditText
    private lateinit var etCantidad: EditText
    private lateinit var etCantidadMinima: EditText
    private lateinit var cbHabilitado: CheckBox
    private lateinit var btnGuardar: Button
    private lateinit var btnEliminar: Button
    private lateinit var btnCancelar: Button

    private lateinit var controller: ProductoController
    private var productoIdActual: Int = 0
    private var productoOriginal: ProductoEntity? = null
    private var fotoRutaActual: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)

        val db = AppDatabase.invoke(this)
        controller = ProductoController(db.productoDao())

        ivPreviewFoto = findViewById(R.id.ivPreviewFoto)
        btnTomarFoto = findViewById(R.id.btnTomarFoto)
        etNombre = findViewById(R.id.etNombreProducto)
        etCantidad = findViewById(R.id.etCantidad)
        etCantidadMinima = findViewById(R.id.etCantidadMinima)
        cbHabilitado = findViewById(R.id.cbProductoHabilitado)
        btnGuardar = findViewById(R.id.btnGuardarProducto)
        btnEliminar = findViewById(R.id.btnEliminarProducto)
        btnCancelar = findViewById(R.id.btnCancelarProducto)

        productoIdActual = intent.getIntExtra("PRODUCTO_ID", 0)

        if (productoIdActual != 0) {
            btnGuardar.text = "Actualizar Producto"
            btnEliminar.visibility = View.VISIBLE
            cargarDatosParaEdicion()
        } else {
            cbHabilitado.isChecked = true
        }

        btnTomarFoto.setOnClickListener {
            abrirCamara { uri ->
                if (uri != null) {
                    fotoRutaActual = uri.toString()
                    ivPreviewFoto.setImageURI(uri)
                } else {
                    Toast.makeText(this, "Foto cancelada", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnGuardar.setOnClickListener { guardarProducto() }
        btnEliminar.setOnClickListener { confirmarEliminacion() }
        btnCancelar.setOnClickListener { finish() }
    }

    private fun cargarDatosParaEdicion() {
        lifecycleScope.launch(Dispatchers.IO) {
            productoOriginal = controller.obtenerProductoPorId(productoIdActual)
            withContext(Dispatchers.Main) {
                productoOriginal?.let {
                    etNombre.setText(it.nombre)
                    etCantidad.setText(it.cantidad.toString())
                    etCantidadMinima.setText(it.cantidadMinima.toString())
                    cbHabilitado.isChecked = it.habilitado

                    fotoRutaActual = it.imagenUrl ?: ""
                    if (fotoRutaActual.isNotBlank()) {
                        ivPreviewFoto.setImageURI(Uri.parse(fotoRutaActual))
                    }
                }
            }
        }
    }

    private fun guardarProducto() {
        val nombreStr = etNombre.text.toString()
        val cantidadStr = etCantidad.text.toString()
        val minStr = etCantidadMinima.text.toString()

        if (nombreStr.isBlank()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            return
        }

        val cantidadFinal = if (cantidadStr.isNotBlank()) cantidadStr.toInt() else 0
        val minFinal = if (minStr.isNotBlank()) minStr.toInt() else 0

        val productoAGuardar = ProductoEntity(
            id = productoIdActual,
            nombre = nombreStr,
            imagenUrl = fotoRutaActual,
            cantidad = cantidadFinal,
            cantidadMinima = minFinal,
            habilitado = cbHabilitado.isChecked,
            ultimaInteraccion = System.currentTimeMillis()
        )

        lifecycleScope.launch(Dispatchers.IO) {
            controller.guardarProducto(productoAGuardar)
            withContext(Dispatchers.Main) {
                Toast.makeText(this@NuevoProductoActivity, "Guardado con éxito", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun confirmarEliminacion() {
        MaterialAlertDialogBuilder(this)
            .setTitle("¿Eliminar producto?")
            .setMessage("Esta acción no se puede deshacer. El producto desaparecerá del inventario permanentemente.")
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setPositiveButton("Eliminar") { _, _ ->
                eliminarProductoDefinitivamente()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun eliminarProductoDefinitivamente() {
        val idParaBorrar = if (productoIdActual != 0) productoIdActual else intent.getIntExtra("PRODUCTO_ID", -1)

        if (idParaBorrar != -1) {
            lifecycleScope.launch(Dispatchers.IO) {
                val productoABorrar = controller.obtenerProductoPorId(idParaBorrar)
                if (productoABorrar != null) {
                    controller.eliminarProducto(productoABorrar)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@NuevoProductoActivity, "Producto eliminado con éxito", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }
}