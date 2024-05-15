package com.example.apptrendlink_valentina

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class ModificarInformacionPerfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modificar_informacion_perfi)

        val inflater = layoutInflater
        inflater.inflate(R.layout.barra_menu, null)

        // Cambiar el color de los iconos
        cambiarColorIcono(R.id.inicio, R.drawable.hogar, "#828282")
        cambiarColorIcono(R.id.buscar, R.drawable.busqueda, "#828282")
        cambiarColorIcono(R.id.diseniar, R.drawable.diseniar, "#828282")
        cambiarColorIcono(R.id.mensajes, R.drawable.mensajes, "#828282")
        cambiarColorIcono(R.id.perfil, R.drawable.usuario, "#3A0CA3")
    }
    private fun cambiarColorIcono(idImageView: Int, idDrawable: Int, colorHex: String) {
        val imageView = findViewById<ImageView>(idImageView)
        val drawable = ContextCompat.getDrawable(this, idDrawable)
        drawable?.setTint(android.graphics.Color.parseColor(colorHex))
        imageView.setImageDrawable(drawable)
    }
}