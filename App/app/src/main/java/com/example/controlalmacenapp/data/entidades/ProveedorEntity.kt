package com.example.controlalmacenapp.data.entidades

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProveedorEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cif: String,
    val nombreComp: String,
    val cantidad: Double,
    val pagado: Boolean,
    val fecha: Long
)
{



}