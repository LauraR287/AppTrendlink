package com.lrincon.trendlink

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Colaboradroes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_disenio)

        val masButton: ImageButton = findViewById(R.id.mas)
        val colaboradoresButton: Button = findViewById(R.id.boton_colaboradores)

        masButton.setOnClickListener {
            // Cambiar la visibilidad del botón de colaboradores a visible
            colaboradoresButton.visibility = View.VISIBLE
        }
    }
}
