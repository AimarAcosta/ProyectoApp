package com.example.controlalmacenapp.view.usuario

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.controlalmacenapp.R
import com.example.controlalmacenapp.model.entities.UsuarioEntity

class UsuarioAdapter(
    private val usuarios: List<UsuarioEntity>,
    private val onUsuarioClick: (UsuarioEntity) -> Unit
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre: TextView = view.findViewById(R.id.tvNombreUsuario)
        val ivFoto: ImageView = view.findViewById(R.id.ivFotoUsuario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.tvNombre.text = usuario.nombre

        holder.ivFoto.setImageResource(R.drawable.perfil)

        holder.itemView.setOnClickListener { onUsuarioClick(usuario) }
    }

    override fun getItemCount() = usuarios.size
}