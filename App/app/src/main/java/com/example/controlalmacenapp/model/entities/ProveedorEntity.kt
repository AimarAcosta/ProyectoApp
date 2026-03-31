package com.example.controlalmacenapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "proveedores")
data class ProveedorEntity(
    @PrimaryKey
    @ColumnInfo(name = "cif")
    val cif: String,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "telefono")
    val telefono: String = "",

    @ColumnInfo(name = "email")
    val email: String = "",

    @ColumnInfo(name = "habilitado")
    val habilitado: Boolean = true
)