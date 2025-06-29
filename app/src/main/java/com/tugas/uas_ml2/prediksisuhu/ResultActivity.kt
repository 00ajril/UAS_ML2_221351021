package com.tugas.uas_ml2.prediksisuhu

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.tugas.uas_ml2.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val result = intent.getFloatExtra("prediction_result", 0f)
        val resultText: TextView = findViewById(R.id.text_result)
        resultText.text = "Prediksi Suhu Maksimum: %.2f °C".format(result)

        val backButton: Button = findViewById(R.id.btn_back)
        backButton.setOnClickListener {
            finish()
        }
    }
}
