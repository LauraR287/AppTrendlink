package com.lrincon.trendlink

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class BusquedaFragment : Fragment(R.layout.fragment_busqueda) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBuscar = view.findViewById<ImageButton>(R.id.buscar)
        val textBusqueda = view.findViewById<EditText>(R.id.busqueda)
        val textResultados = view.findViewById<TextView>(R.id.textViewEmail)

        buttonBuscar.setOnClickListener {
            val busqueda = textBusqueda.text.toString()

            if (busqueda == "usuarios"||busqueda == "Usuarios")  {
                val auth = FirebaseAuth.getInstance()
                val usuario = auth.currentUser

                usuario?.let {
                    textResultados.setText(usuario.displayName)
                }
            }
        }
    }
}
