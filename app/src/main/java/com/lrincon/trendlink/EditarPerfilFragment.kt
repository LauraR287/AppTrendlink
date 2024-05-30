package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditarPerfilFragment: Fragment(R.layout.fragment_modificar_perfil) {

    private lateinit var database: DatabaseReference
    private lateinit var firebaseHelper: FirebaseHelperUser

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonAtras: Button = view.findViewById(R.id.atras)
        val buttonGuardarCambios: Button = view.findViewById(R.id.buttonChanges)

        val editTextUser: EditText = view.findViewById(R.id.editTextUser)
        val descripcionUser: EditText = view.findViewById(R.id.DescripcionUser)

        database = FirebaseDatabase.getInstance().reference.child("usuarios")
        firebaseHelper = FirebaseHelperUser()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.email?.replace(".", ",")

        buttonAtras.setOnClickListener {
            val fragment = PerfilFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonGuardarCambios.setOnClickListener {
            val nombre = editTextUser.text.toString()
            val descripcion = descripcionUser.text.toString()

            Log.i("USUARIO ID", userId.toString())

            if (userId != null) {
                firebaseHelper.modificarInfo(database, userId, nombre, descripcion)
                val fragment = PerfilFragment()
                val transaction = parentFragmentManager.beginTransaction()
                transaction.replace(R.id.containerView, fragment)
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                Log.e("EditarPerfilFragment", "No authenticated user found")
            }
        }
    }
}