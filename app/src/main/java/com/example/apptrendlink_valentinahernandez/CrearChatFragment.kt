package com.example.apptrendlink_valentinahernandez

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class CrearChatFragment: Fragment(R.layout.fragment_perfil_otro_usuario)  {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAbrirChat = view.findViewById<ImageButton>(R.id.imageMensaje)

        buttonAbrirChat.setOnClickListener {
            val intent = Intent(requireContext(), MensajesFragment::class.java)
            startActivity(intent)
        }
    }
}