package com.example.desafio2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafio2.R
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.example.desafio2.views.MainActivity

class LoginActivity : AppCompatActivity() {

    // referencia FirebaseAuth
    private lateinit var auth: FirebaseAuth

    // componentes de la vista
    private lateinit var btnLogin: Button
    private lateinit var textViewRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.editTextEmailAddress).text.toString()
            val password = findViewById<EditText>(R.id.txtPassword).text.toString()
            this.login(email, password)
        }

        textViewRegister = findViewById(R.id.textViewRegister)
        textViewRegister.setOnClickListener {
            this.goToRegister()
        }
    }

    private fun login(email: String, password: String)
    {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ task ->
            if(task.isSuccessful)
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener{ exception ->
            Toast.makeText(applicationContext, exception.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    private fun goToRegister(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}