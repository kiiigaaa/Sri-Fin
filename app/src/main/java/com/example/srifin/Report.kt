package com.example.srifin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class Report : AppCompatActivity() {
    private lateinit var mainIncomeProgressBar: ProgressBar
    private lateinit var additionalIncomeProgressBar: ProgressBar
    private lateinit var otherIncomeProgressBar: ProgressBar
    private lateinit var mainIncomeTextView: TextView
    private lateinit var additionalIncomeTextView: TextView
    private lateinit var otherIncomeTextView: TextView
    private lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        mainIncomeProgressBar = findViewById(R.id.progressBar4)
        additionalIncomeProgressBar = findViewById(R.id.progressBar6)
        otherIncomeProgressBar = findViewById(R.id.progressBar7)
        mainIncomeTextView = findViewById(R.id.textView4)
        additionalIncomeTextView = findViewById(R.id.textView5)
        otherIncomeTextView = findViewById(R.id.textView6)
        backButton = findViewById(R.id.button4)

        backButton.setOnClickListener{
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
            finish()
        }
    }
}
