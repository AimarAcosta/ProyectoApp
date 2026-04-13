package com.example.controlalmacenapp.view.albaran

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.entities.AlbaranEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AlbaranAdapter(
    private var albaranes: List<AlbaranEntity>,
    private val onItemClick: (AlbaranEntity) -> Unit
) : RecyclerView.Adapter<AlbaranAdapter.AlbaranViewHolder>() {

    class AlbaranViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvFecha: TextView = view.findViewById(R.id.tvFechaAlbaran)
        val tvImporte: TextView = view.findViewById(R.id.tvImporteAlbaran)
        val tvEstadoPago: TextView = view.findViewById(R.id.tvEstadoPago)
        val tvTieneFoto: TextView = view.findViewById(R.id.tvTieneFoto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbaranViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_albaran, parent, false)
        return AlbaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbaranViewHolder, position: Int) {
        val albaran = albaranes[position]

        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val fechaFormateada = sdf.format(Date(albaran.fechaEmision))

        holder.tvFecha.text = "Fecha: $fechaFormateada"
        holder.tvImporte.text = String.format("%.2f €", albaran.importe)

        if (albaran.pagado) {
            holder.tvEstadoPago.text = "Pagado"
            holder.tvEstadoPago.setTextColor(Color.parseColor("#4CAF50")) // Verde
        } else {
            holder.tvEstadoPago.text = "Pendiente de Pago"
            holder.tvEstadoPago.setTextColor(Color.parseColor("#F44336")) // Rojo
        }

        if (!albaran.fotoUri.isNullOrBlank()) {
            holder.tvTieneFoto.visibility = View.VISIBLE
        } else {
            holder.tvTieneFoto.visibility = View.GONE
        }

        holder.itemView.setOnClickListener { onItemClick(albaran) }
    }

    override fun getItemCount(): Int = albaranes.size

    fun actualizarLista(nuevaLista: List<AlbaranEntity>) {
        albaranes = nuevaLista
        notifyDataSetChanged()
    }
}