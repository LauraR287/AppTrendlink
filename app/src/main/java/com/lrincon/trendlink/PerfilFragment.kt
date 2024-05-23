package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class PerfilFragment: Fragment(R.layout.fragment_perfil) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEditarPerfil = view.findViewById<ImageButton>(R.id.EditarPerfil)
        val buttonCerrarSesion = view.findViewById<ImageView>(R.id.CerrarSesionLogo)
        val TextCerrarSesion = view.findViewById<TextView>(R.id.CerrarSesion)

        buttonEditarPerfil.setOnClickListener {
            val fragment = EditarPerfilFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonCerrarSesion.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        TextCerrarSesion.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
    }
}