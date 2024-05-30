package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PerfilFragment: Fragment(R.layout.fragment_perfil) {

    private lateinit var database: DatabaseReference
    private lateinit var firebaseHelper: FirebaseHelperUser
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonEditarPerfil = view.findViewById<ImageButton>(R.id.EditarPerfil)
        val buttonCerrarSesion = view.findViewById<ImageView>(R.id.CerrarSesionLogo)
        val TextCerrarSesion = view.findViewById<TextView>(R.id.CerrarSesion)

        database = FirebaseDatabase.getInstance().getReference("usuarios")
        firebaseHelper = FirebaseHelperUser()

        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.email?.replace(".", ",")

        Log.i("USUARIO ID", userId.toString())

        val descripcion = view.findViewById<TextView>(R.id.textDescripcionPerfil)
        val nombre = view.findViewById<TextView>(R.id.textNombreEnPerfil)

        if (userId != null) {
            val userDatabaseRef = database.child(userId)
            firebaseHelper.consultarUser(userDatabaseRef, nombre, descripcion)
        }

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