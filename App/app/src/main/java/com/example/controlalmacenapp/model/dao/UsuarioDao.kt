package com.example.controlalmacenapp.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlalmacenapp.model.entities.UsuarioEntity

@Dao
interface UsuarioDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUsuario(usuario: UsuarioEntity): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUsuarioLista(usuarios: List<UsuarioEntity>): List<Long>

    @Update
    suspend fun updateUsuario(usuario: UsuarioEntity): Int

    @Delete
    suspend fun deleteUsuario(usuario: UsuarioEntity): Int

    @Query("SELECT * FROM usuarios WHERE habilitado = 1 ORDER BY nombre ASC")
    suspend fun getUsuariosHabilitados(): List<UsuarioEntity>

    @Query("SELECT * FROM usuarios WHERE nombre = :nombre AND password = :password AND es_administrador = 1 AND habilitado = 1")
    suspend fun loginAdmin(nombre: String, password: String): UsuarioEntity?
}