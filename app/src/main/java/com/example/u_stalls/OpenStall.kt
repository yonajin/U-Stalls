package com.example.u_stalls

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class OpenStall : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_stall)

        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.order -> startActivity(Intent(this, OrderFragment::class.java))
                R.id.overview -> startActivity(Intent(this, OverviewFragment::class.java))
                R.id.queue -> startActivity(Intent(this, QueueFragment::class.java))
                R.id.menu -> startActivity(Intent(this, MenuFragment::class.java))

            }
            finish()
            true
        }
    }


    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            toast.cancel()
            finishAffinity()
        } else {
            toast = Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT)
            toast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
