package com.example.controlalmacenapp.view

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.controller.ProductoController
import com.example.controlalmacenapp.model.database.AppDatabase
import com.example.controlalmacenapp.model.entities.ProductoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NuevoProductoActivity : BaseActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_producto)

        val db = AppDatabase.invoke(this)
        controller = ProductoController(db.productoDao())

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
            cbHabilitado.isChecked = true // Por defecto activo al crear
        }

        btnGuardar.setOnClickListener { guardarProducto() }
        btnEliminar.setOnClickListener { confirmarEliminacion() }
        btnCancelar.setOnClickListener { finish() }
    }

    private fun cargarDatosParaEdicion() {
        lifecycleScope.launch(Dispatchers.IO) {
            productoOriginal = controller.obtenerProducto(productoIdActual)
            withContext(Dispatchers.Main) {
                productoOriginal?.let {
                    etNombre.setText(it.nombre)
                    etCantidad.setText(it.cantidad.toString())
                    etCantidadMinima.setText(it.cantidadMinima.toString())
                    cbHabilitado.isChecked = it.habilitado
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
            imagenUrl = productoOriginal?.imagenUrl ?: "",
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
        AlertDialog.Builder(this)
            .setTitle("Eliminar Producto")
            .setMessage("¿Estás seguro de que deseas eliminar este producto? Esto no se puede deshacer.")
            .setPositiveButton("Eliminar") { _, _ ->
                lifecycleScope.launch(Dispatchers.IO) {
                    productoOriginal?.let { controller.eliminarProducto(it) }
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@NuevoProductoActivity, "Producto eliminado", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }
}