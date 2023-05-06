package com.example.srifin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Other_Category : AppCompatActivity() {

    private lateinit var categoryNameEditText: EditText

    private lateinit var dRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_category)

        categoryNameEditText = findViewById(R.id.editTextTextPersonName4)

        val EnterButton = findViewById<Button>(R.id.button3)
        EnterButton.setOnClickListener {
            val intent = Intent(this, notify::class.java)

            startActivity(intent)
            finish()
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

        // Write a message to the database
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("categories").push()

        myRef.child("category_name").setValue(categoryName)
            .addOnSuccessListener {
                categoryNameEditText.text.clear()

                val intent = Intent(this, notify::class.java)

                startActivity(intent)
                finish()
                saveDataToFirebase()
            }
            .addOnFailureListener {
                // Handle any errors here
            }
    }
}
