package com.example.controlalmacenapp.model.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlalmacenapp.model.entities.ProductoEntity

interface ProductoDao {
    @Insert( onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProducto(producto: ProductoEntity): Long

    @Insert( onConflict = OnConflictStrategy.ABORT)
    suspend fun insertProductoLista(productos: List <ProductoEntity>):List <Long>

    @Update
    suspend fun updateProducto(producto: ProductoEntity): Int

    @Delete
    suspend fun deleteProducto(producto: ProductoEntity): Int

    @Query("SELECT * FROM ProductoEntity")
    suspend fun getProductos():List<ProductoEntity>
}
