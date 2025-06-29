package com.tugas.uas_ml2.dataset

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tugas.uas_ml2.R

class DatasetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dataset)

        val backButton: Button = findViewById(R.id.btn_back_dataset)
        backButton.setOnClickListener {
            finish()
        }

        val kaggleButton: Button = findViewById(R.id.btn_open_kaggle)
        kaggleButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.kaggle.com/datasets/ananthr1/weather-prediction")
            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
