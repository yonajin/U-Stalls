package com.example.u_stalls

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TermsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        val acceptButton: Button = findViewById(R.id.acceptButton)
        acceptButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java) // Corrected the intent
            startActivity(intent) // Start the LoginActivity
        }
    }
}
