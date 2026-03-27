package com.example.controlalmacenapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlalmacenapp.model.entities.ProductoEntity

@Dao
interface ProductoDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProducto(producto: ProductoEntity): Long

    @Update
    suspend fun updateProducto(producto: ProductoEntity): Int

    @Delete
    suspend fun deleteProducto(producto: ProductoEntity): Int

    @Query("SELECT * FROM productos ORDER BY nombre ASC")
    suspend fun obtenerTodosLosProductos(): List<ProductoEntity>

    @Query("SELECT * FROM productos WHERE habilitado = 1 ORDER BY ultima_interaccion DESC LIMIT 10")
    suspend fun obtenerProductosHabituales(): List<ProductoEntity>

    @Query("SELECT * FROM productos WHERE habilitado = 1 AND nombre LIKE '%' || :patron || '%' ORDER BY nombre ASC")
    suspend fun buscarProductosPorNombre(patron: String): List<ProductoEntity>

    @Query("UPDATE productos SET cantidad = cantidad + 1, ultima_interaccion = :timestamp WHERE id = :id")
    suspend fun incrementarStock(id: Int, timestamp: Long = System.currentTimeMillis())

    @Query("UPDATE productos SET cantidad = cantidad - 1, ultima_interaccion = :timestamp WHERE id = :id AND cantidad > 0")
    suspend fun decrementarStock(id: Int, timestamp: Long = System.currentTimeMillis())

    @Query("SELECT * FROM productos WHERE id = :id LIMIT 1")
    suspend fun obtenerProductoPorId(id: Int): ProductoEntity?
}