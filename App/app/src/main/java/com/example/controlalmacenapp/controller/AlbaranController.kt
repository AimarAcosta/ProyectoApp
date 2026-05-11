package com.example.controlalmacenapp.controller

import com.example.controlalmacenapp.model.dao.AlbaranDao
import com.example.controlalmacenapp.model.entities.AlbaranEntity

class AlbaranController(private val dao: AlbaranDao) {

    suspend fun guardarAlbaran(albaran: AlbaranEntity): Long {
        return dao.insertAlbaran(albaran)
    }

    suspend fun actualizarAlbaran(albaran: AlbaranEntity) {
        dao.updateAlbaran(albaran)
    }

    suspend fun eliminarAlbaran(albaran: AlbaranEntity) {
        dao.deleteAlbaran(albaran)
    }

    suspend fun obtenerAlbaran(id: Int): AlbaranEntity? {
        return dao.obtenerAlbaranPorId(id)
    }

    suspend fun obtenerAlbaranesDeProveedor(cif: String): List<AlbaranEntity> {
        return dao.obtenerAlbaranesDeProveedor(cif)
    }

    suspend fun obtenerPendientesDePago(): List<AlbaranEntity> {
        return dao.obtenerAlbaranesPendientes()
    }

    suspend fun obtenerTodosLosAlbaranes(): List<AlbaranEntity> {
        return dao.obtenerTodosLosAlbaranes()
    }
}