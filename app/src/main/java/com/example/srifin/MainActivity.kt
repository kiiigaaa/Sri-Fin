package com.example.srifin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.content.Intent
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var radioButton: RadioButton
    private lateinit var radioButton5: RadioButton
    private lateinit var radioButton8: RadioButton
    private lateinit var editTextTextPersonName: EditText
    private lateinit var button16: Button
    private lateinit var imageView7: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        radioButton = findViewById(R.id.radioButton)
        radioButton5 = findViewById(R.id.radioButton5)
        radioButton8 = findViewById(R.id.radioButton8)
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName)
        button16 = findViewById(R.id.button16)
        imageView7 = findViewById(R.id.imageView7)

        button16.setOnClickListener {
            val intent = when {
                radioButton.isChecked -> Intent(this, Amount::class.java)
                radioButton5.isChecked -> Intent(this, Amount::class.java)
                radioButton8.isChecked -> Intent(this, Other_Category::class.java)
                else -> null
            }

            intent?.let {
                startActivity(it)
            }
        }
    }
}
