package com.example.controlalmacenapp.controller

import com.example.controlalmacenapp.model.dao.UsuarioDao
import com.example.controlalmacenapp.model.entities.UsuarioEntity

class UsuarioController(private val dao: UsuarioDao) {

    suspend fun inicializarDatosSiVacio() {
        val adminExiste = dao.obtenerUsuarioPorNombre("ADMIN")

        if (adminExiste == null) {
            val admin = UsuarioEntity(
                nombre = "ADMIN",
                password = "admin",
                esAdministrador = true,
                habilitado = true,
                imagenUrl = ""
            )
            val empleado = UsuarioEntity(
                nombre = "Aimar",
                password = "aimar",
                esAdministrador = false,
                habilitado = true,
                imagenUrl = ""
            )

            dao.insertUsuarioLista(listOf(admin, empleado))
        }
    }

    suspend fun validarLogin(nombre: String, pass: String): Boolean {
        val usuario = dao.obtenerUsuarioPorNombre(nombre)
        return usuario != null && usuario.password == pass && usuario.habilitado
    }

    suspend fun obtenerTodosLosUsuarios(): List<UsuarioEntity> {
        return dao.getUsuariosHabilitados()
    }
}