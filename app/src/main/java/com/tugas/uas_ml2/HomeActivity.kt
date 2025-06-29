package com.tugas.uas_ml2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tugas.uas_ml2.about.AboutActivity
import com.tugas.uas_ml2.feature.FeatureActivity
import com.tugas.uas_ml2.splash.ArchitectureActivity
import com.tugas.uas_ml2.databinding.ActivityHomeBinding
import com.tugas.uas_ml2.dataset.DatasetActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimulasi.setOnClickListener {
            startActivity(Intent(this, MainPredictActivity::class.java)) // ganti sesuai nama halaman prediksi
        }
        binding.btnTentang.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
        binding.btnFitur.setOnClickListener {
            startActivity(Intent(this, FeatureActivity::class.java))
        }
        binding.btnModel.setOnClickListener {
            startActivity(Intent(this, ArchitectureActivity::class.java))
        }
        binding.btnDataset.setOnClickListener {
            startActivity(Intent(this, DatasetActivity::class.java))
        }

    }
}
