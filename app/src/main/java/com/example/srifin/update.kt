package com.example.srifin


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.srifin.R
import com.google.firebase.database.FirebaseDatabase

class update : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val titleEditText = findViewById<EditText>(R.id.Title)
        val descriptionEditText = findViewById<EditText>(R.id.Des)
        val updateButton = findViewById<Button>(R.id.btnUpData)

        updateButton.setOnClickListener {
            // Get the values of the title and description fields
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            // Update the values in the Firebase database
            val database = FirebaseDatabase.getInstance().reference
            val updatesRef = database.child("updates")
            val newUpdateRef = updatesRef.push()
            val update = Update(title, description)
            newUpdateRef.setValue(update)

            // Clear the fields
            titleEditText.text.clear()
            descriptionEditText.text.clear()
        }
    }

    data class Update(val title: String = "", val description: String = "")
}
