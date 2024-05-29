package com.lrincon.trendlink

import android.util.Log
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

data class usuario(val apellido: String, val email: String, val nombre: String, val descripcion: String)

class FirebaseHelperUser {
    fun newUser(database: DatabaseReference, nombre: String, apellido: String, email:String, descripcion: String) {
        val usuarioId = "$nombre $apellido"

        if (usuarioId != null) {
            val Usuario = usuario(apellido, email, nombre, descripcion)
            database.child(usuarioId).setValue(Usuario)
        }
    }

    fun consultarUsers(database: DatabaseReference, editText: TextView) {
        database.get()
            .addOnSuccessListener { dataSnapshot ->
                Log.i("Informacion", dataSnapshot.toString())
                if (dataSnapshot.exists()) {
                    val userIds = mutableListOf<String>()
                    for (snapshot in dataSnapshot.children) {
                        val userId = snapshot.key
                        userId?.let {
                            userIds.add(it)
                        }
                    }
                    val userIdsString = userIds.joinToString(separator = "\n")
                    editText.text = userIdsString
                } else {
                    // Handle the case where no data exists under "usuarios"
                    editText.text = "No hay usuarios disponibles"
                }
            }
            .addOnFailureListener { exception ->
                // Handle any errors that occurred during the data retrieval process
                editText.text = "Error fetching users: ${exception.message}"
            }
    }

    fun modificarInfo(database: DatabaseReference, nombre: String, descripcion: String) {
        database.child("usuarios").get().addOnSuccessListener { dataSnapshot ->
            for (userSnapshot in dataSnapshot.children) {
                val userId = userSnapshot.key ?: continue
                val updates = mapOf<String, Any>(
                    "nombre" to nombre,
                    "descripcion" to descripcion
                )
                database.child("usuarios").child(userId).updateChildren(updates)
            }
        }.addOnFailureListener { exception ->
            exception.printStackTrace()
        }
    }
}