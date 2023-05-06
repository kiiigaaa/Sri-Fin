package com.example.srifin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class Control : AppCompatActivity() {

    private lateinit var tvId: TextView
    private lateinit var tvTit: TextView
    private lateinit var tvDes: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control)
        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            val intent = Intent(this, update::class.java)
            intent.putExtra("Id", intent.getStringExtra("Id"))
            intent.putExtra("Title", intent.getStringExtra("Title"))
            intent.putExtra("Description", intent.getStringExtra("Description"))
            startActivity(intent)
        }
        btnDelete.setOnClickListener {
            deleteRecord(
                intent.getStringExtra("Id").toString()
            )
        }
    }


    private fun initView() {
        tvId = findViewById(R.id.tvNwsId)
        tvDes = findViewById(R.id.tvNwsDes)
        tvTit = findViewById(R.id.tvNwsTit)


        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        tvId.text = intent.getStringExtra("Id")
        tvDes.text = intent.getStringExtra("Title")
        tvTit.text = intent.getStringExtra("Description")
    }

    private fun deleteRecord(id: String) {
        val dbRef = FirebaseDatabase.getInstance().getReference("Control").child(id)
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "deleted", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

}
