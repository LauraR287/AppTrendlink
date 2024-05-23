package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditarPerfilFragment: Fragment(R.layout.fragment_modificar_perfil) {

    private lateinit var database: DatabaseReference
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras = view.findViewById<Button>(R.id.atras)
        val buttonGuardarCambios = view.findViewById<Button>(R.id.buttonChanges)

        val editTextUser = view.findViewById<EditText>(R.id.editTextUser)
        val descripcionUser = view.findViewById<EditText>(R.id.DescripcionUser)

        database = FirebaseDatabase.getInstance().reference.child("usuarios")

        buttonAtras.setOnClickListener {
            val fragment = PerfilFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonGuardarCambios.setOnClickListener {
            val editTextUser = editTextUser.text.toString()
            val descripcionUser = descripcionUser.text.toString()

            val firebaseHelperUser = FirebaseHelperUser()
            firebaseHelperUser.modificarInfo(database,editTextUser, descripcionUser)
        }

    }
}