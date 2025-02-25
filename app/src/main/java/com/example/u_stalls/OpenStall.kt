package com.example.u_stalls

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class OpenStall : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_open_stall)

        val btnClose: Button = findViewById(R.id.btnOpen)
        btnClose.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            startActivity(intent)
            finish() // Closes the current activity
        }

        val ordershistory1: Button = findViewById(R.id.tvOrdersHistory)
        ordershistory1.setOnClickListener {
            val intent = Intent(this, OrdersHistoryActivity::class.java)
            startActivity(intent)
            finish() // Closes the current activity
        }

        val overviewBtn: ImageButton = findViewById(R.id.overview_btn)
        overviewBtn.setOnClickListener {
            val intent = Intent(this, OverviewActivity::class.java)
            startActivity(intent)
            finish() // Closes the current activity
        }

        val queueBtn: ImageButton = findViewById(R.id.queue_btn)
        queueBtn.setOnClickListener {
            val intent = Intent(this, QueueActivity::class.java)
            startActivity(intent)
            finish() // Closes the current activity
        }

    }


    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast.cancel() // Cancel previous toast
            finishAffinity() // Exit the app
        } else {
            toast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT)
            toast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}