package com.tugas.uas_ml2.feature

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tugas.uas_ml2.R

class FeatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature)

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
