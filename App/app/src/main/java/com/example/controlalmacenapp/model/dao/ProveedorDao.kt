package com.example.controlalmacenapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlalmacenapp.model.entities.ProveedorEntity

@Dao
interface ProveedorDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProveedor(proveedor: ProveedorEntity)

    @Update
    suspend fun updateProveedor(proveedor: ProveedorEntity)

    @Delete
    suspend fun deleteProveedor(proveedor: ProveedorEntity)

    @Query("SELECT * FROM proveedores WHERE habilitado = 1 ORDER BY nombre ASC")
    suspend fun obtenerProveedoresActivos(): List<ProveedorEntity>

    @Query("SELECT * FROM proveedores WHERE cif = :cif LIMIT 1")
    suspend fun obtenerProveedorPorCif(cif: String): ProveedorEntity?
}