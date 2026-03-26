package com.example.controlalmacenapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "nombre") val nombre: String,

    @ColumnInfo(name = "imagenUrl") val imagenUrl: String?,

    @ColumnInfo(name = "cantidad") val cantidad: Int = 0,

    @ColumnInfo(name = "cantidad_minima") val cantidadMinima: Int = 0,

    @ColumnInfo(name = "habilitado") val habilitado: Boolean = true,

    @ColumnInfo(name = "ultima_interaccion") val ultimaInteraccion: Long = System.currentTimeMillis()
)