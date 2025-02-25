package com.example.u_stalls

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ResetPasswordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val stallId = findViewById<EditText>(R.id.stall_id)
        val newPassword = findViewById<EditText>(R.id.new_password)
        val btnSubmit = findViewById<Button>(R.id.btnSendCode)

        btnBack.setOnClickListener {
            finish()
        }

        btnSubmit.setOnClickListener {
            val stallIdText = stallId.text.toString()
            val newPasswordText = newPassword.text.toString()

            if (stallIdText.isEmpty() || newPasswordText.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Password reset successful!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}