package com.example.controlalmacenapp.data.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.controlalmacenapp.data.entidades.UsuarioEntity

interface UsuarioDao {
    @Insert( onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUsuario(usuario: UsuarioEntity): Long

    @Insert( onConflict = OnConflictStrategy.ABORT)
    suspend fun insertUsuarioLista(usuarios: List <UsuarioEntity>):List <Long>

    @Update
    suspend fun updateUsuario(usuario: UsuarioEntity): Int

    @Delete
    suspend fun deleteUsuario(usuario: UsuarioEntity): Int

    @Query("SELECT * FROM UsuarioEntity")
    suspend fun getUsuarios():List<UsuarioEntity>
}