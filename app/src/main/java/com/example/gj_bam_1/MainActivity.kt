package com.example.gj_bam_1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val saveButton: Button = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            // zadanie 1 - wysłanie wartości wprowadzonej przez użytkownika do nowej aktywności
            val intent = Intent(this, UserActivity::class.java)
            val inputValue: String = findViewById<EditText?>(R.id.enterName).text.toString()
            intent.putExtra("name", inputValue)
            startActivity(intent)
        }
    }
}