package com.example.srifin

import android.content.ClipDescription
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Other_Category : AppCompatActivity() {

    private lateinit var categoryNameEditText: EditText
    private lateinit var categoryDescriptionEditText: EditText
    private lateinit var dRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_category)

        categoryNameEditText = findViewById(R.id.catName)
        categoryDescriptionEditText = findViewById(R.id.catDescription)

        val enterButton = findViewById<Button>(R.id.button3)
        enterButton.setOnClickListener {
            saveDataToFirebase()
        }

        val backButton = findViewById<Button>(R.id.button2)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun saveDataToFirebase() {
        val categoryName: String = categoryNameEditText.text.toString()
        val categoryDescription: String = categoryDescriptionEditText.text.toString()

        if (categoryName.isEmpty()) {
            categoryNameEditText.error = "Category name cannot be empty"
            return
        }

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val categoriesRef = database.getReference("categories")

        // Generate a unique ID for the new category
        val categoryId = categoriesRef.push().key

        val categoryMap = hashMapOf(
            "categoryname" to categoryName,
            "categorydescription" to categoryDescription,
            "collectionid" to categoryId
        )

        if (categoryId != null) {
            categoriesRef.child(categoryId).setValue(categoryMap)
                .addOnSuccessListener {
                    categoryNameEditText.text.clear()
                    categoryDescriptionEditText.text.clear()

                    val intent = Intent(this, notify::class.java)
                    startActivity(intent)
                    finish()
                }
                .addOnFailureListener {
                    // Handle any errors here
                }
        }
    }

}
