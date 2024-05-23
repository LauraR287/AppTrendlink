package com.lrincon.trendlink

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class PasswordRecoveryActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var inicioSesionButton: TextView

    private lateinit var currentUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        emailEditText = findViewById(R.id.email)
        saveButton = findViewById(R.id.code)
        inicioSesionButton = findViewById(R.id.inicioSesion)
        currentUser = FirebaseAuth.getInstance().currentUser!!

        saveButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isNotEmpty()) {
                Toast.makeText(this, "Sigue las instrucciones enviadas a tu correo", Toast.LENGTH_SHORT).show()
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                finish()
            } else {
                Toast.makeText(this, "Por favor ingresa un correo", Toast.LENGTH_SHORT).show()
            }
        }

        inicioSesionButton.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

