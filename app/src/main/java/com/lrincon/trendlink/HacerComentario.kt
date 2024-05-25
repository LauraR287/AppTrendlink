package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HacerComentario : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_inicio)

        val comentarioButton= findViewById<ImageView>(R.id.comentario)

        comentarioButton.setOnClickListener {
            Log.i("App","Supuestamente")
            val intent = Intent(this, ComentarioFragment::class.java)
            startActivity(intent)
        }
    }
}