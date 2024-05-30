package com.example.apptrendlink_valentinahernandez

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.apptrendlink_valentinahernandez.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/*
controlador que maneja la lógica de navegación entre
diferentes fragmentosde la aplicación
 */

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        var inicioFragment = InicioFragment()
        var busquedaFragment = BusquedaFragment()
        var crearFragment = CrearFragment()
        var mensajesFragment = MensajesFragment()
        var perfilFragment = PerfilFragment()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_inicio -> {
                    setCurrentFragment(inicioFragment)
                    true
                }
                R.id.nav_buscar -> {
                    setCurrentFragment(busquedaFragment)
                    true
                }
                R.id.nav_crear -> {
                    setCurrentFragment(crearFragment)
                    true
                }
                R.id.nav_mensajes -> {
                    setCurrentFragment(mensajesFragment)
                    true
                }
                R.id.nav_perfil -> {
                    setCurrentFragment(perfilFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.containerView, fragment)
            commit()
        }
    }
}