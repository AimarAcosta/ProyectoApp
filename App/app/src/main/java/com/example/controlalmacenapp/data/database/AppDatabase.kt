package com.example.controlalmacenapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.controlalmacenapp.data.entidades.ProductoEntity
import com.example.controlalmacenapp.data.entidades.ProveedorEntity
import com.example.controlalmacenapp.data.entidades.UsuarioEntity

@Database(entities = [UsuarioEntity::class, ProductoEntity::class, ProveedorEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    //Singleton pattern
    companion object {
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "myDatabase"
        )
            .build()

    }
}