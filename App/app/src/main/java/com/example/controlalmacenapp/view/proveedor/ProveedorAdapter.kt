package com.example.controlalmacenapp.view.proveedor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.entities.ProveedorEntity

class ProveedorAdapter(
    private var proveedores: List<ProveedorEntity>,
    private val onItemClick: (ProveedorEntity) -> Unit
) : RecyclerView.Adapter<ProveedorAdapter.ProveedorViewHolder>() {

    class ProveedorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreProveedor)
        val tvCif: TextView = view.findViewById(R.id.tvCif)
        val tvTelefono: TextView = view.findViewById(R.id.tvTelefono)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProveedorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_proveedor, parent, false)
        return ProveedorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProveedorViewHolder, position: Int) {
        val proveedor = proveedores[position]

        holder.tvNombre.text = proveedor.nombre
        holder.tvCif.text = "CIF: ${proveedor.cif}"

        if (proveedor.telefono.isNotBlank()) {
            holder.tvTelefono.text = "Tel: ${proveedor.telefono}"
        } else {
            holder.tvTelefono.text = "Sin teléfono"
        }

        holder.itemView.setOnClickListener { onItemClick(proveedor) }
    }

    override fun getItemCount(): Int = proveedores.size

    fun actualizarLista(nuevaLista: List<ProveedorEntity>) {
        proveedores = nuevaLista
        notifyDataSetChanged()
    }
}