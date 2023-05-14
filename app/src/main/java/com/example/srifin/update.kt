package com.example.srifin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class Update : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)


        val categoryname = intent.getStringExtra("categoryname")
        val categorydescription = intent.getStringExtra("categorydescription")
        val collectionid = intent.getStringExtra("collectionid")


        val descriptionEditText = findViewById<EditText>(R.id.updatecatDescription)
        val nameEditText = findViewById<EditText>(R.id.updatecatName)
        val updateButton = findViewById<Button>(R.id.updatebtn)
        val deleteButton = findViewById<Button>(R.id.deletebtn)


        nameEditText.hint = categoryname
        descriptionEditText.hint = categorydescription



        //delete

        deleteButton.setOnClickListener {
            val categoryRef = collectionid?.let { it1 ->
                FirebaseDatabase.getInstance().getReference("categories").child(
                    it1
                )
            }
            if (categoryRef != null) {
                categoryRef.removeValue()
            }
            Toast.makeText(this, "Category collection deleted successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }

        //update
        updateButton.setOnClickListener {
            val categoryRef = collectionid?.let { it1 ->
                FirebaseDatabase.getInstance().getReference("categories").child(
                    it1
                )
            }
            val newName = nameEditText.text.toString()
            val newDescription = descriptionEditText.text.toString()
            val updatedCategory = mapOf("categoryname" to newName, "categorydescription" to newDescription)
            if (categoryRef != null) {
                categoryRef.setValue(updatedCategory)
            }
            Toast.makeText(this, "Category collection updated successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }



    }
}