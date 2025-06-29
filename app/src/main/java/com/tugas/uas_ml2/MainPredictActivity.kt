package com.tugas.uas_ml2

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tugas.uas_ml2.databinding.ActivityMainPredictBinding
import com.tugas.uas_ml2.prediksisuhu.ResultActivity
import com.tugas.uas_ml2.tflitehelper.TFLiteHelper

class MainPredictActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainPredictBinding
    private lateinit var liteHelper: TFLiteHelper

    private val weatherOptions = arrayOf("Cerah", "Drizzle", "Fog", "Rain", "Snow")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Prediksi Suhu"
            setDisplayHomeAsUpEnabled(true)
        }

        liteHelper = TFLiteHelper(this)

        binding.seekWeather.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val label = weatherOptions.getOrNull(progress) ?: "Cerah"
                binding.textWeather.text = "Cuaca: $label"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        binding.btnPredict.setOnClickListener {
            val p = binding.inputPrecipitation.text.toString().toFloatOrNull()
            val tMin = binding.inputTempMin.text.toString().toFloatOrNull()
            val wind = binding.inputWind.text.toString().toFloatOrNull()
            val weather = weatherOptions.getOrElse(binding.seekWeather.progress) { "Cerah" }

            if (p == null || tMin == null || wind == null) {
                Toast.makeText(this, "Semua data harus diisi dengan benar", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val inputArray = prepareInputArray(p, tMin, wind, weather)
            val prediction = liteHelper.predictTemperature(inputArray)

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("prediction_result", prediction)
            startActivity(intent)
        }

        binding.btnBackHome.setOnClickListener {
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun prepareInputArray(p: Float, tMin: Float, w: Float, weather: String): FloatArray {
        val weatherKey = when (weather.lowercase()) {
            "drizzle" -> "drizzle"
            "fog"     -> "fog"
            "rain"    -> "rain"
            "snow"    -> "snow"
            "cerah", "sun", "clear" -> "sun"
            else -> "sun"
        }

        val oneHot = when (weatherKey) {
            "drizzle" -> floatArrayOf(1f, 0f, 0f, 0f)
            "fog"     -> floatArrayOf(0f, 1f, 0f, 0f)
            "rain"    -> floatArrayOf(0f, 0f, 1f, 0f)
            "snow"    -> floatArrayOf(0f, 0f, 0f, 1f)
            else      -> floatArrayOf(0f, 0f, 0f, 0f)
        }

        val normalized = liteHelper.normalize(floatArrayOf(p, tMin, w))
        return normalized + oneHot
    }
}
