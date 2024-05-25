package com.lrincon.trendlink

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class like_activity:AppCompatActivity() {
    private var isCorazonLleno = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_inicio)

        val corazonButton:ImageButton=findViewById(R.id.like)
        corazonButton.setOnClickListener {
            if (isCorazonLleno) {
                corazonButton.setImageResource(R.drawable.corazon_vacio)
            } else {
                corazonButton.setImageResource(R.drawable.corazon)
            }
            isCorazonLleno = !isCorazonLleno
        }
    }
}