package com.example.srifin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase

class Amount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_amount)

        val amountEditText = findViewById<EditText>(R.id.editTextTextPersonName3)
        val backButton = findViewById<Button>(R.id.button)
        val enterButton = findViewById<Button>(R.id.button20)

        // Set click listener for the "Back" button
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Set click listener for the "Enter" button
        enterButton.setOnClickListener {
            val amount = amountEditText.text.toString()

            if (amount.isEmpty()) {
                amountEditText.error = "Amount cannot be empty"
                return@setOnClickListener
            }

            // Save the entered amount to Firebase database
            val database = FirebaseDatabase.getInstance()
            val amountRef = database.getReference("amounts").push()
            amountRef.setValue(amount)

            // Navigate to the Report activity
            startActivity(Intent(this, Report::class.java))
        }
    }
}
