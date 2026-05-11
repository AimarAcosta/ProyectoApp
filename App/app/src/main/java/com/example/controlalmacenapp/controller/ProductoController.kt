package com.example.controlalmacenapp.controller

import com.example.controlalmacenapp.model.dao.ProductoDao
import com.example.controlalmacenapp.model.entities.ProductoEntity

class ProductoController(private val dao: ProductoDao) {

    suspend fun obtenerInventarioCompleto(): List<ProductoEntity> {
        return dao.obtenerTodosLosProductos()
    }

    suspend fun obtenerProductosDiario(): List<ProductoEntity> {
        return dao.obtenerProductosHabituales()
    }

    suspend fun buscarProductos(patron: String): List<ProductoEntity> {
        return dao.buscarProductosPorNombre(patron)
    }

    suspend fun obtenerProductoPorId(id: Int): ProductoEntity? {
        return dao.obtenerProductoPorId(id)
    }

    suspend fun sumarStock(id: Int) {
        dao.incrementarStock(id)
    }

    suspend fun restarStock(producto: ProductoEntity): Boolean {
        if (producto.cantidad > 0) {
            dao.decrementarStock(producto.id)
            return true
        }
        return false
    }

    suspend fun guardarProducto(producto: ProductoEntity) {
        if (producto.id == 0) {
            dao.insertProducto(producto)
        } else {
            dao.updateProducto(producto)
        }
    }

    suspend fun eliminarProducto(producto: ProductoEntity) {
        dao.deleteProducto(producto)
    }

    fun estaEnStockCritico(producto: ProductoEntity): Boolean {
        return producto.cantidad <= producto.cantidadMinima
    }
}