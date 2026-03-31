package com.example.controlalmacenapp.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.os.Environment
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

open class BaseActivity : AppCompatActivity() {

    private val timeoutHandler = Handler(Looper.getMainLooper())
    private val timeoutRunnable = Runnable {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private val TIEMPO_INACTIVIDAD = 600000L

    override fun onResume() {
        super.onResume()
        reiniciarTemporizador()
    }

    override fun onPause() {
        super.onPause()
        detenerTemporizador()
    }

    override fun onUserInteraction() {
        super.onUserInteraction()
        reiniciarTemporizador()
    }

    private fun reiniciarTemporizador() {
        detenerTemporizador()
        timeoutHandler.postDelayed(timeoutRunnable, TIEMPO_INACTIVIDAD)
    }

    private fun detenerTemporizador() {
        timeoutHandler.removeCallbacks(timeoutRunnable)
    }

    private var fotoUriActual: Uri? = null

    private var onFotoTomada: ((Uri?) -> Unit)? = null

    private val tomarFotoLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { exito ->
        if (exito && fotoUriActual != null) {
            onFotoTomada?.invoke(fotoUriActual)
        } else {
            onFotoTomada?.invoke(null)
        }
    }

    fun abrirCamara(callback: (Uri?) -> Unit) {
        onFotoTomada = callback
        val archivoFoto = crearArchivoFisico()

        if (archivoFoto != null) {
            fotoUriActual = FileProvider.getUriForFile(
                this,
                "${applicationContext.packageName}.fileprovider",
                archivoFoto
            )
            tomarFotoLauncher.launch(fotoUriActual!!)
        } else {
            callback(null)
        }
    }

    private fun crearArchivoFisico(): File? {
        return try {
            val fecha = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            File.createTempFile("IMG_${fecha}_", ".jpg", directorio)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}