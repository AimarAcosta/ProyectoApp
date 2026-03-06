package com.example.controlalmacenapp.data.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.controlalmacenapp.data.entidades.ProveedorEntity

interface ProveedorDao {

    @Insert
    suspend fun insert(nota: ProveedorEntity)

    @Query("SELECT * FROM ProveedorEntity ORDER BY fecha DESC")
    suspend fun getAll(): List<ProveedorEntity>

}