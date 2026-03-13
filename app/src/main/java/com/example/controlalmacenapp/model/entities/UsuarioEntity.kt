package com.example.controlalmacenapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "nombre") val nombre: String,

    @ColumnInfo(name = "imagenUrl") val imagenUrl: String?,

    @ColumnInfo(name = "es_administrador") val esAdministrador: Boolean,

    @ColumnInfo(name = "password") val password: String? = null,

    @ColumnInfo(name = "email") val email: String? = null,

    @ColumnInfo(name = "habilitado") val habilitado: Boolean = true
)