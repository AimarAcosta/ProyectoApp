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
import com.google.android.material.card.MaterialCardView

class ProductoAdapter(
    private var productos: List<ProductoEntity>,
    private val onSumarClick: (ProductoEntity) -> Unit,
    private val onRestarClick: (ProductoEntity) -> Unit,
    private val onSumarCincoClick: (ProductoEntity) -> Unit,
    private val onRestarCincoClick: (ProductoEntity) -> Unit,
    private val onItemClick: (ProductoEntity) -> Unit
) : RecyclerView.Adapter<ProductoAdapter.ProductoViewHolder>() {

    class ProductoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardProducto: MaterialCardView = view.findViewById(R.id.cardProducto)
        val vIndicadorAlerta: View = view.findViewById(R.id.vIndicadorAlerta)
        val ivProducto: ImageView = view.findViewById(R.id.ivProducto)
        val tvNombreProducto: TextView = view.findViewById(R.id.tvNombreProducto)
        val tvCantidad: TextView = view.findViewById(R.id.tvCantidad)
        val btnSumar: Button = view.findViewById(R.id.btnSumar)
        val btnRestar: Button = view.findViewById(R.id.btnRestar)
        val btnRestarCinco: Button = view.findViewById(R.id.btnRestarCinco)
        val btnSumarCinco: Button = view.findViewById(R.id.btnSumarCinco)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = productos[position]

        holder.tvNombreProducto.text = producto.nombre

        if (!producto.imagenUrl.isNullOrBlank()) {
            holder.ivProducto.setImageURI(android.net.Uri.parse(producto.imagenUrl))
        } else {
            holder.ivProducto.setImageDrawable(null)
        }

        if (producto.cantidad <= producto.cantidadMinima) {
            holder.vIndicadorAlerta.visibility = View.VISIBLE
            holder.cardProducto.strokeWidth = 3
            holder.tvCantidad.setTextColor(Color.parseColor("#E74C3C"))
            holder.tvCantidad.text = "Stock: ${producto.cantidad} ⚠️"
        } else {
            holder.vIndicadorAlerta.visibility = View.GONE
            holder.cardProducto.strokeWidth = 0
            holder.tvCantidad.setTextColor(Color.parseColor("#7F8C8D"))
            holder.tvCantidad.text = "Stock: ${producto.cantidad}"
        }

        holder.btnSumar.setOnClickListener { onSumarClick(producto) }
        holder.btnRestar.setOnClickListener { onRestarClick(producto) }
        holder.btnSumarCinco.setOnClickListener { onSumarCincoClick(producto) }
        holder.btnRestarCinco.setOnClickListener { onRestarCincoClick(producto) }

        holder.itemView.setOnClickListener { onItemClick(producto) }
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<ProductoEntity>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}