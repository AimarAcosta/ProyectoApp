package com.example.controlalmacenapp.controller

import com.example.controlalmacenapp.model.dao.ProveedorDao
import com.example.controlalmacenapp.model.entities.ProveedorEntity

class ProveedorController(private val dao: ProveedorDao) {

    suspend fun obtenerProveedoresActivos(): List<ProveedorEntity> {
        return dao.obtenerProveedoresActivos()
    }

    suspend fun obtenerProveedor(cif: String): ProveedorEntity? {
        return dao.obtenerProveedorPorCif(cif)
    }

    suspend fun guardarProveedor(proveedor: ProveedorEntity) {
        val existe = dao.obtenerProveedorPorCif(proveedor.cif)
        if (existe == null) {
            dao.insertProveedor(proveedor)
        } else {
            dao.updateProveedor(proveedor)
        }
    }

    suspend fun eliminarProveedor(proveedor: ProveedorEntity) {
        dao.deleteProveedor(proveedor)
    }
}