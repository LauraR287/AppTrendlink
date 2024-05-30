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
        val editTextApellido: EditText = view.findViewById(R.id.editTextApellido)
        val descripcionUser: EditText = view.findViewById(R.id.DescripcionUser)
        val user: TextView = view.findViewById(R.id.Nombre)

        database = FirebaseDatabase.getInstance().reference.child("usuarios")
        firebaseHelper = FirebaseHelperUser()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.email?.replace(".", ",")

        if (userId != null) {
            val userDatabaseRef = database.child(userId)
            firebaseHelper.consultarUser(userDatabaseRef, user, null)
        }

        buttonAtras.setOnClickListener {
            val fragment = PerfilFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.containerView, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        buttonGuardarCambios.setOnClickListener {
            val user_name = editTextUser.text.toString()
            val apellido = editTextApellido.text.toString()
            val descripcion = descripcionUser.text.toString()

            Log.e("UWSERNAME", user_name)

            val userId = currentUser?.email?.replace(".", ",")

            if (userId != null) {
                firebaseHelper.modificarInfo(database, userId, user_name, apellido, descripcion)
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