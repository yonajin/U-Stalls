package com.example.u_stalls

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class MenuFragment : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var etProductName: EditText
    private lateinit var etProductPrice: EditText
    private lateinit var spinnerCategory: Spinner
    private lateinit var etDescription: EditText
    private lateinit var btnUpload: Button
    private var imageUri: Uri? = null

    private val firestore = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_menu, container, false)

        imageView = view.findViewById(R.id.imageViewFood)
        etProductName = view.findViewById(R.id.etProductName)
        etProductPrice = view.findViewById(R.id.etProductPrice)
        spinnerCategory = view.findViewById(R.id.spinnerCategory)
        etDescription = view.findViewById(R.id.etDescription)
        btnUpload = view.findViewById(R.id.btnUpload)

        val categories = arrayOf("Meals", "Snacks", "Drinks")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)
        spinnerCategory.adapter = adapter

        imageView.setOnClickListener { openGallery() }
        btnUpload.setOnClickListener { uploadFoodItem() }

        return view
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data
            imageView.setImageURI(imageUri)
        }
    }

    private fun uploadFoodItem() {
        val productName = etProductName.text.toString().trim()
        val productPrice = etProductPrice.text.toString().trim()
        val category = spinnerCategory.selectedItem.toString()
        val description = etDescription.text.toString().trim()

        if (productName.isEmpty() || productPrice.isEmpty() || imageUri == null) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val storageRef = storage.reference.child("food_images/${UUID.randomUUID()}.jpg")
        storageRef.putFile(imageUri!!)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    saveFoodToFirestore(productName, productPrice, category, description, uri.toString())
                }
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Image upload failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun saveFoodToFirestore(name: String, price: String, category: String, description: String, imageUrl: String) {
        val foodItem = hashMapOf(
            "name" to name,
            "price" to price,
            "category" to category,
            "description" to description,
            "imageUrl" to imageUrl
        )

        firestore.collection("menu").add(foodItem)
            .addOnSuccessListener {
                Toast.makeText(requireContext(), "Food item added!", Toast.LENGTH_SHORT).show()
                resetFields()
            }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to add item", Toast.LENGTH_SHORT).show()
            }
    }

    private fun resetFields() {
        etProductName.text.clear()
        etProductPrice.text.clear()
        etDescription.text.clear()
        imageView.setImageResource(R.drawable.placeholder_image)
    }
}
