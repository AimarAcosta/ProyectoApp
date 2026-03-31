package com.example.controlalmacenapp.controller

import com.example.controlalmacenapp.model.dao.AlbaranDao
import com.example.controlalmacenapp.model.entities.AlbaranEntity

class AlbaranController(private val dao: AlbaranDao) {

    suspend fun obtenerAlbaranesPorProveedor(cif: String): List<AlbaranEntity> {
        return dao.obtenerAlbaranesDeProveedor(cif)
    }

    suspend fun obtenerAlbaranesPendientes(): List<AlbaranEntity> {
        return dao.obtenerAlbaranesPendientes()
    }

    suspend fun guardarAlbaran(albaran: AlbaranEntity) {
        if (albaran.id == 0) {
            dao.insertAlbaran(albaran)
        } else {
            dao.updateAlbaran(albaran)
        }
    }

    suspend fun eliminarAlbaran(albaran: AlbaranEntity) {
        dao.deleteAlbaran(albaran)
    }
}