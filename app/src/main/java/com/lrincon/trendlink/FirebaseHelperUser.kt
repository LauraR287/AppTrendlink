package com.lrincon.trendlink

import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference

data class usuario(
    val apellido: String = "",
    val email: String = "",
    val nombre: String = "",
    val descripcion: String = ""
)

class FirebaseHelperUser {
    fun newUser(database: DatabaseReference, nombre: String, apellido: String, email:String, descripcion: String) {
        val usuarioId = email.replace(".", ",")

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
                    val userInfoList = mutableListOf<String>()
                    for (snapshot in dataSnapshot.children) {
                        val usuario = snapshot.getValue(usuario::class.java)
                        usuario?.let {
                            val userInfo = "${it.nombre} ${it.apellido}"
                            userInfoList.add(userInfo)
                        }
                    }
                    val userInfoString = userInfoList.joinToString(separator = "\n")
                    editText.text = userInfoString
                }
            }
    }

    fun consultarUser(database: DatabaseReference, nombreTextView: TextView, descripcionTextView: TextView?) {
        database.get()
            .addOnSuccessListener { dataSnapshot ->
                Log.i("Informacion", dataSnapshot.toString())
                if (dataSnapshot.exists()) {
                    val usuario = dataSnapshot.getValue(usuario::class.java)
                    usuario?.let {
                        val userInfo = "${it.nombre} ${it.apellido}"
                        nombreTextView.text = userInfo
                        if (descripcionTextView != null) {
                            descripcionTextView.text = it.descripcion
                        }
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseError", "Error al obtener los datos del usuario", exception)
            }
    }

    fun modificarInfo(database: DatabaseReference, usuarioId: String, nombre: String?, apellido: String?, descripcion: String?) {
        val updates = mutableMapOf<String, Any>()

        if (!nombre.isNullOrEmpty()) {
            updates["nombre"] = nombre
            Log.i("NOMBRE", nombre)
        }
        if (!apellido.isNullOrEmpty()) {
            updates["apellido"] = apellido
            Log.i("APELLIDO", apellido)
        }
        if (!descripcion.isNullOrEmpty()) {
            updates["descripcion"] = descripcion
            Log.i("DESCRIPCION", descripcion)
        }
        if (updates.isNotEmpty()) {
            database.child(usuarioId).updateChildren(updates)
                .addOnSuccessListener {
                    Log.i("Firebase", "Actualización exitosa")
                }
                .addOnFailureListener { exception ->
                    Log.e("Firebase", "Error al actualizar", exception)
                }
            Log.i("UPDATES", updates.toString())
        } else {
            Log.i("Firebase", "No hay actualizaciones que realizar")
        }
    }


}