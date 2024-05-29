package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewAccountActivity: AppCompatActivity() {
    private lateinit var Auth: FirebaseAuth
    private lateinit var textNombre: EditText
    private lateinit var textApellido: EditText
    private lateinit var textEmail: EditText
    private lateinit var textContraseña: EditText
    private lateinit var textVerificacionContraseña: EditText
    private lateinit var buttonRegistrar: Button
    private lateinit var textIniciarSesion: TextView

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        Auth = FirebaseAuth.getInstance()

        // Obtener referencias a los componentes de la UI
        textNombre = findViewById(R.id.textNombre)
        textApellido = findViewById(R.id.textApellido)
        textEmail = findViewById(R.id.textEmail)
        textContraseña = findViewById(R.id.textContraseña)
        textVerificacionContraseña = findViewById(R.id.textVerificacionContraseña)
        textIniciarSesion = findViewById(R.id.textIniciarSesion)
        buttonRegistrar = findViewById(R.id.buttonRegistrar)

        database = FirebaseDatabase.getInstance().reference.child("usuarios")

        // Configurar el listener del botón de registro
        buttonRegistrar.setOnClickListener {
            val nombre = textNombre.text.toString().trim()
            val apellido = textApellido.text.toString().trim()
            val email = textEmail.text.toString().trim()
            val password = textContraseña.text.toString().trim()
            val passwordVerficacion = textVerificacionContraseña.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese el correo y la contraseña", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password != passwordVerficacion){
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = Auth.currentUser
                        Toast.makeText(this, "Registro exitoso.", Toast.LENGTH_SHORT).show()

                        val firebaseHelperUser = FirebaseHelperUser()
                        firebaseHelperUser.newUser(database, nombre, apellido, email, "")

                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error en el registro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }
        textIniciarSesion.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}