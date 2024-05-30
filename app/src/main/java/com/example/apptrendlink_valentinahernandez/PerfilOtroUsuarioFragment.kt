package com.example.apptrendlink_valentinahernandez

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class PerfilOtroUsuarioFragment : Fragment(R.layout.fragment_perfil_otro_usuario) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonChat = view.findViewById<ImageView>(R.id.imageMensaje)
        buttonChat.setOnClickListener {
            val fragment = MensajesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}