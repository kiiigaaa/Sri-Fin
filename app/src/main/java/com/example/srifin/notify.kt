package com.example.srifin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class notify : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var okButton: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify)

        textView = findViewById(R.id.textView)
        okButton = findViewById(R.id.button5)
        imageView = findViewById(R.id.imageView5)

        okButton.setOnClickListener {
            val intent = Intent(this@notify, Read::class.java)
            startActivity(intent)
        }
    }
}
