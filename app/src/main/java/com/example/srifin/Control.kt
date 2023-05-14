package com.example.srifin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase

class Control : AppCompatActivity() {
    private lateinit var etNwsTit: EditText
    private lateinit var etNwsDes: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)

        // Initialize views
        etNwsTit = findViewById(R.id.tvNwsTit)
        etNwsDes = findViewById(R.id.tvNwsDes)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnView = findViewById<Button>(R.id.btnUpdate2)

        // Set click listeners for buttons
        btnUpdate.setOnClickListener {
            // Get the values entered by the user
            val title = etNwsTit.text.toString()
            val description = etNwsDes.text.toString()

            // Push the updates to Firebase database
            val database = FirebaseDatabase.getInstance()
            val myRef = database.reference
            myRef.child("title").setValue(title)
            myRef.child("description").setValue(description)
                .addOnSuccessListener {
                    // Show success message
                    Toast.makeText(
                        this@Control,
                        "Updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Clear the EditText fields
                    etNwsTit.text.clear()
                    etNwsDes.text.clear()
                }
                .addOnFailureListener {
                    // Show error message
                    Toast.makeText(
                        this@Control,
                        "Failed to update",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
        btnView.setOnClickListener {
            // Navigate to Read.kt activity
            val intent = Intent(this@Control, Read::class.java)
            startActivity(intent)
        }
    }
}
