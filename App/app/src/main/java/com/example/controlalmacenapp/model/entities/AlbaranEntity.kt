package com.example.controlalmacenapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "albaranes",
    foreignKeys = [
        ForeignKey(
            entity = ProveedorEntity::class,
            parentColumns = ["cif"],
            childColumns = ["proveedor_cif"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class AlbaranEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "proveedor_cif", index = true)
    val proveedorCif: String,

    @ColumnInfo(name = "fecha_emision")
    val fechaEmision: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "importe")
    val importe: Double,

    @ColumnInfo(name = "pagado")
    val pagado: Boolean = false,

    @ColumnInfo(name = "fecha_pago")
    val fechaPago: Long? = null,

    @ColumnInfo(name = "foto_uri")
    val fotoUri: String? = null
)