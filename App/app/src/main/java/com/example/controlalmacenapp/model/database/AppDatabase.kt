package com.example.controlalmacenapp.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.controlalmacenapp.model.dao.ProductoDao
import com.example.controlalmacenapp.model.entities.AlbaranEntity
import com.example.controlalmacenapp.model.entities.ProductoEntity
import com.example.controlalmacenapp.model.entities.ProveedorEntity
import com.example.controlalmacenapp.model.entities.UsuarioEntity
import com.example.controlalmacenapp.model.dao.UsuarioDao

@Database(
    entities = [UsuarioEntity::class, ProductoEntity::class, ProveedorEntity::class, AlbaranEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao

    abstract fun productoDao(): ProductoDao

    // Singleton pattern
    companion object {
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "myDatabase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}