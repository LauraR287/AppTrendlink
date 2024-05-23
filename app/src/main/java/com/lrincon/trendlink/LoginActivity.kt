package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity: AppCompatActivity() {

    private lateinit var editTextUser: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var forgotPassword: TextView
    private lateinit var newAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextUser = findViewById(R.id.editTextUser)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        forgotPassword = findViewById<TextView>(R.id.forgotPassword)
        newAccount = findViewById<TextView>(R.id.newAccount)

        buttonLogin.setOnClickListener {
            val user = editTextUser.text.toString()
            val password = editTextPassword.text.toString()

            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor ingresa usuario y contraseña", Toast.LENGTH_SHORT).show()

            } else {
                // Aquí puedes realizar la validación del inicio de sesión con el servidor o alguna otra lógica
                // Inicializar Firebase Auth
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(user, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Inicio de sesión exitoso, el usuario está autenticado
                            val user = auth.currentUser
                            // Hacer algo con el usuario autenticado
                            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MenuActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Fallo en el inicio de sesión, mostrar mensaje de error
                            Toast.makeText(this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        forgotPassword.setOnClickListener {
            val intent = Intent(this, PasswordRecoveryActivity::class.java)
            startActivity(intent)
        }

        newAccount.setOnClickListener {
            val intent = Intent(this, NewAccountActivity::class.java)
            startActivity(intent)
        }

    }
}
