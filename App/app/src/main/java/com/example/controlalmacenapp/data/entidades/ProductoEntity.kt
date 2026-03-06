package com.example.controlalmacenapp.data.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductoEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre") val Nombre: String?,
    @ColumnInfo(name = "cantidad")val Cantidad: Double?,
    @ColumnInfo(name = "cantidadMinima")val CantidadMinima: Double?,
    @ColumnInfo(name = "imagenUrl")val ImagenUrl: String?
){
}