package com.example.controlalmacenapp.view.producto

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.entities.ProductoEntity

class ProductoAdapter(
    private var productos: List<ProductoEntity>,
    private val onSumarClick: (ProductoEntity) -> Unit,
    private val onRestarClick: (ProductoEntity) -> Unit,
    private val onItemClick: (ProductoEntity) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivProducto: ImageView = view.findViewById(R.id.ivProducto)
        val tvNombreProducto: TextView = view.findViewById(R.id.tvNombreProducto)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val btnSumar: Button = view.findViewById(R.id.btnSumar)
        val btnRestar: Button = view.findViewById(R.id.btnRestar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]

        holder.tvNombreProducto.text = producto.nombre
        holder.tvCantidad.text = "Stock: ${producto.cantidad}"

        if (producto.cantidad <= producto.cantidadMinima) {
            holder.tvCantidad.setTextColor(Color.RED)
            holder.tvCantidad.text = "Stock: ${producto.cantidad} ⚠️ (Crítico)"
        } else {
            holder.tvCantidad.setTextColor(Color.parseColor("#333333"))
        }

        holder.btnSumar.setOnClickListener { onSumarClick(producto) }
        holder.btnRestar.setOnClickListener { onRestarClick(producto) }

        holder.itemView.setOnClickListener { onItemClick(producto) }
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<ProductoEntity>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}