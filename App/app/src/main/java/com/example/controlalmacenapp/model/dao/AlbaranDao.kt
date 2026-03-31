package com.example.controlalmacenapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlalmacenapp.model.entities.AlbaranEntity

@Dao
interface AlbaranDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAlbaran(albaran: AlbaranEntity): Long

    @Update
    suspend fun updateAlbaran(albaran: AlbaranEntity)

    @Delete
    suspend fun deleteAlbaran(albaran: AlbaranEntity)

    @Query("SELECT * FROM albaranes WHERE proveedor_cif = :cif ORDER BY fecha_emision DESC")
    suspend fun obtenerAlbaranesDeProveedor(cif: String): List<AlbaranEntity>

    @Query("SELECT * FROM albaranes WHERE pagado = 0 ORDER BY fecha_emision ASC")
    suspend fun obtenerAlbaranesPendientes(): List<AlbaranEntity>
}