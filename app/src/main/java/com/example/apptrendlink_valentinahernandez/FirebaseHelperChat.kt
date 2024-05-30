package com.example.apptrendlink_valentinahernandez

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*

data class Mensaje(
    val contenido: String = "",
    val timestamp: Long = 0L
)

class FirebaseHelperChat {

    fun consultarChat(database: DatabaseReference, callback: (List<Chat>) -> Unit) {
        database.child("chats").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val chats = mutableListOf<Chat>()
                snapshot.children.forEach {
                    val chat = it.getValue(Chat::class.java)
                    chat?.let { chats.add(it) }
                }
                callback(chats)
            }

            override fun onCancelled(error: DatabaseError) {
                // Manejar error
            }
        })
    }

    fun enviarMensaje(databaseChats: DatabaseReference, mensaje: String) {
        val nuevoMensaje = Mensaje(mensaje, System.currentTimeMillis())
        databaseChats.push().setValue(nuevoMensaje)
    }

    fun eliminarChat(databaseChats: DatabaseReference) {
        databaseChats.removeValue()
    }
}




