package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class CrearComentarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_comentario)
        val commentButton = findViewById<ImageButton>(R.id.comment_button)
        commentButton.setOnClickListener { // Crear un Intent para la actividad de "hacer_comentario.xml"
            val intent = Intent(
                this@CrearComentarioActivity,
                HacerComentarioActivity::class.java
            )
            // Iniciar la actividad
            startActivity(intent)
        }
    }
}