package com.example.controlalmacenapp.data.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre") val Nombre: String?,
    @ColumnInfo(name = "email")val Email: String?,
    @ColumnInfo(name = "password")val Password: String?,
    @ColumnInfo(name = "tipo")val Tipo: String?,
    @ColumnInfo(name = "imagenUrl")val ImagenUrl: String?

) {

}