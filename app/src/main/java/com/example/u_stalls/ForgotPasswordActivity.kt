package com.example.u_stalls

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val btnSendCode = findViewById<Button>(R.id.btnSendCode)

        btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnSendCode.setOnClickListener {
            val phone = etPhone.text.toString()
            if (phone.isNotEmpty()) {
                Toast.makeText(this, "Send code clicked for: $phone", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, OtpVerification::class.java)
                startActivity(intent)
            } else {
                etPhone.error = "Please enter your phone number"
            }
        }
    }
}