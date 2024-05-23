package com.lrincon.trendlink

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class BusquedaFragment : Fragment(R.layout.fragment_busqueda) {

    private lateinit var database: DatabaseReference

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonBuscar = view.findViewById<ImageButton>(R.id.buscar)
        val textBusqueda = view.findViewById<EditText>(R.id.busqueda)
        val textResultados = view.findViewById<TextView>(R.id.textViewUsers)

        database = FirebaseDatabase.getInstance().reference.child("usuarios")

        buttonBuscar.setOnClickListener {
            val busquedaText = textBusqueda.text.toString().toLowerCase()
            if(busquedaText == "usuarios") {
                val firebaseHelperUser = FirebaseHelperUser()
                firebaseHelperUser.consultarUsers(database, textResultados)
            } else {
                textResultados.text = "Lo sentimos, solo puedes consultar usuarios. " +
                        "Puedes hacerlo con la palabra 'usuarios'"
            }
        }
    }
}
