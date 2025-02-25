package com.example.u_stalls

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize UI components
        val stallId = findViewById<EditText>(R.id.stall_id)
        val password = findViewById<EditText>(R.id.password)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val rememberMe = findViewById<CheckBox>(R.id.remember_me)
        val forgotPassword = findViewById<TextView>(R.id.forgot_password)
        val intent1 = Intent(this, OrderActivity::class.java)


        // Handle login button click
        btnLogin.setOnClickListener {
            val email = stallId.text.toString() + "@ueat.com"  // Example: Convert Stall ID to email
            val pass = password.text.toString()

            // Simulate login process (for testing without Firebase)
            if (email == "test@ueat.com" && pass == "password123") {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                startActivity(intent1)  // Close the current activity (go to the next one)
            }
            else {
                Toast.makeText(this, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
            }
        }

        // Handle forgot password text click
        forgotPassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}