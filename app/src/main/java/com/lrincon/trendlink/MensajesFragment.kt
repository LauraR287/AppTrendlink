package com.lrincon.trendlink

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.ImageButton

class MensajesFragment: Fragment(R.layout.fragment_mensajes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAbrirChat = view.findViewById<ImageButton>(R.id.chat)

        buttonAbrirChat.setOnClickListener {
            val fragment = Fragment(R.layout.bandeja_mensajes)
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

            val buttonAtras = view.findViewById<ImageButton>(R.id.atras)
            buttonAtras.setOnClickListener {
                val fragment = Fragment(R.layout.fragment_mensajes)
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.containerView, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }

        }
    }

    
}