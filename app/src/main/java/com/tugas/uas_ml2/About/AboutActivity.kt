package com.tugas.uas_ml2.about

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tugas.uas_ml2.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val backButton: Button? = findViewById(R.id.btn_back)
        backButton?.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
