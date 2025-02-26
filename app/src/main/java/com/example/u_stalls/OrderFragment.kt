package com.example.u_stalls

import android.app.Dialog
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.core.text.HtmlCompat

class OrderFragment : Fragment() {
    private var backPressedTime: Long = 0
    private lateinit var toast: Toast

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val order: ImageButton = view.findViewById(R.id.order_btn)
        val overview: ImageButton = view.findViewById(R.id.overview_btn)
        val queue: ImageButton = view.findViewById(R.id.queue_btn)
        val menu: ImageButton = view.findViewById(R.id.menu_btn)
        val orderHistory: Button = view.findViewById(R.id.tvOrdersHistory)
        val orderText: TextView = view.findViewById(R.id.ordertxt)

        // Correct underline implementation
        val underlinedText = SpannableString("1.2 Underline using SpannableString")
        underlinedText.setSpan(UnderlineSpan(), 0, underlinedText.length, 0)
        orderText.text = underlinedText

        // Navigation and Dialog Actions
        order.setOnClickListener { findNavController().navigate(R.id.orderFragment) }
        overview.setOnClickListener { findNavController().navigate(R.id.overviewFragment) }
        queue.setOnClickListener { findNavController().navigate(R.id.queueFragment) }
        menu.setOnClickListener { findNavController().navigate(R.id.menuFragment) }
        orderHistory.setOnClickListener { findNavController().navigate(R.id.ordersHistoryFragment) }

        val btnClose: Button = view.findViewById(R.id.btnClose)
        btnClose.setOnClickListener {
            findNavController().navigate(R.id.action_orderFragment_to_openStallFragment)
        }
    }

    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.fragment_button)

        val btnExit: ImageButton = dialog.findViewById(R.id.exitBtn)
        btnExit.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}
