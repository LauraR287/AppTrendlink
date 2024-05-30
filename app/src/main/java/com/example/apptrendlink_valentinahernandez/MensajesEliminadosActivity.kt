package com.example.apptrendlink_valentinahernandez

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.apptrendlink_valentinahernandez.R

class MensajesEliminadosActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mensajes_eliminados)

        val buttonEliminarChat = findViewById<ImageButton>(R.id.eliminar)
        val buttonPerfil = findViewById<ImageButton>(R.id.perfil)
        val buttonAtras = findViewById<Button>(R.id.atras)


        buttonEliminarChat.setOnClickListener {
            val fragment = ChatEliminadoFragment()
            replaceFragment(fragment)
        }

        buttonAtras.setOnClickListener {
            val fragment = MensajesFragment()
            replaceFragment(fragment)
        }

        buttonPerfil.setOnClickListener {
            //val fragment = PerfilUsuarioFragment()
            //replaceFragment(fragment)
        }
    }

    // Esta función nos permite reemplazar la vista con un Fragment utilizando
    // la vista de activity_main

    private fun replaceFragment(fragment: Fragment) {
        setContentView(R.layout.activity_main)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}