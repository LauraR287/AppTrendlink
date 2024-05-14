package com.lrincon.trendlink

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.content.Intent

class MensajesFragment: Fragment(R.layout.fragment_mensajes) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAbrirChat = view.findViewById<ImageButton>(R.id.chat)

        buttonAbrirChat.setOnClickListener {
            val intent = Intent(requireContext(), MensajesActivity::class.java)
            startActivity(intent)
        }
    }
}