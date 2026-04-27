package com.example.controlalmacenapp.view.inventario

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.entities.ProductoEntity

class InventarioExcelAdapter(
    private var productos: List<ProductoEntity>
) : RecyclerView.Adapter<InventarioExcelAdapter.ExcelViewHolder>() {

    class ExcelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvExcelNombre)
        val tvCantidad: TextView = view.findViewById(R.id.tvExcelCantidad)
        val tvMinimo: TextView = view.findViewById(R.id.tvExcelMinimo)
        val tvEstado: TextView = view.findViewById(R.id.tvExcelEstado)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExcelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fila_excel, parent, false)
        return ExcelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExcelViewHolder, position: Int) {
        val producto = productos[position]

        holder.tvNombre.text = producto.nombre
        holder.tvCantidad.text = producto.cantidad.toString()
        holder.tvMinimo.text = producto.cantidadMinima.toString()

        if (!producto.habilitado) {
            holder.tvEstado.text = "DESHAB."
            holder.tvEstado.setTextColor(Color.parseColor("#9E9E9E"))
            holder.tvCantidad.setTextColor(Color.parseColor("#9E9E9E"))
        } else if (producto.cantidad <= producto.cantidadMinima) {
            holder.tvEstado.text = "ALERTA"
            holder.tvEstado.setTextColor(Color.parseColor("#F44336"))
            holder.tvCantidad.setTextColor(Color.parseColor("#F44336"))
        } else {
            holder.tvEstado.text = "OK"
            holder.tvEstado.setTextColor(Color.parseColor("#4CAF50"))
            holder.tvCantidad.setTextColor(Color.parseColor("#333333"))
        }
    }

    override fun getItemCount(): Int = productos.size

    fun actualizarLista(nuevaLista: List<ProductoEntity>) {
        productos = nuevaLista
        notifyDataSetChanged()
    }
}