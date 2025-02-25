package com.example.u_stalls

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat

class OrderActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val order :ImageButton = findViewById(R.id.order_btn)
        val overview :ImageButton = findViewById(R.id.overview_btn)
        val queue :ImageButton = findViewById(R.id.queue_btn)
        val menu :ImageButton = findViewById(R.id.menu_btn)
        val orderhistory :Button = findViewById(R.id.tvOrdersHistory)

        val ordertext = findViewById<TextView>(R.id.ordertxt)

        ordertext.text = HtmlCompat.fromHtml("<u>1.2 Underline using HtmlCompat.fromHtml()</u> ", HtmlCompat.FROM_HTML_MODE_LEGACY)



        order.setOnClickListener{
            showDialog()
        }
        overview.setOnClickListener{
            showDialog()
        }
        queue.setOnClickListener{
            showDialog()
        }
        menu.setOnClickListener{
            showDialog()
        }
        orderhistory.setOnClickListener{
            showDialog()
        }

        // Close button functionality
        val btnClose: Button = findViewById(R.id.btnClose)
        btnClose.setOnClickListener {
            val intent = Intent(this, OpenStall::class.java)
            startActivity(intent)
            finish() // Closes the current activity
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.fragment_button) // Ensure this XML exists

        val btnExit: ImageButton = dialog.findViewById(R.id.exitBtn) // Ensure this button exists in fragment_button.xml
        btnExit.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    // Handle back press using Intent
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
