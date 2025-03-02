package com.example.u_stalls

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class CloseStallActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_close_stall)

        val orderHistory: Button = findViewById(R.id.tvOrdersHistory)
        val orderText: TextView = findViewById(R.id.ordertxt)
        val btnClose: Button = findViewById(R.id.btnClose)
        val bottomNav: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val underlinedText = SpannableString("1.2 Underline using SpannableString")
        underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, 0)
        orderText.text = underlinedText

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.order, R.id.overview, R.id.queue, R.id.menu -> showDialog()
            }
            true
        }

        orderHistory.setOnClickListener { showDialog() }
        btnClose.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.fragment_button)

        val btnExit: Button = dialog.findViewById(R.id.exitBtn)
        btnExit.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}
