package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserProfile : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var phone: EditText
    private lateinit var job: EditText
    private lateinit var btnSaveData: Button

    private lateinit var viewbtn :Button
    private lateinit var updatebtn :Button
    private lateinit var deletebtn :Button


    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        username = findViewById(R.id.editTextTextPersonName22)
        email = findViewById(R.id.editTextTextPersonName23)
        phone = findViewById(R.id.editTextTextPersonName26)
        job = findViewById(R.id.editTextTextPersonName24)
        btnSaveData = findViewById(R.id.button7)

        viewbtn =findViewById(R.id.viewb)
        updatebtn =findViewById(R.id.updateb)
        deletebtn =findViewById(R.id.deleteb)

        viewbtn.setOnClickListener {
            val intent = Intent(this, Read::class.java)
            startActivity(intent)
        }
        updatebtn.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
        deletebtn.setOnClickListener {
            val intent = Intent(this, Delete::class.java)
            startActivity(intent)
        }

        dbRef = FirebaseDatabase.getInstance().getReference("Users")

        btnSaveData.setOnClickListener {
            saveUserData()
        }
    }private fun saveUserData() {
        //getting values
        val empName = username.text.toString()
        val empEmail = email.text.toString()
        val empPhoneNo = phone.text.toString()
        val empJob = job.text.toString()

        if (empName.isEmpty()) {
            username.error = "Please enter Username"
        }
        if (empEmail.isEmpty()) {
            email.error = "Please enter Email"
        }
        if (empPhoneNo.isEmpty()) {
            phone.error = "Please enter Phone Number"
        }
        if (empJob.isEmpty()) {
            job.error = "Please enter Your Job"
        }
        val user= ProfileModel(empName , empEmail ,empPhoneNo ,empJob )

        dbRef.child(empName).setValue(user)
            .addOnCompleteListener{
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}




