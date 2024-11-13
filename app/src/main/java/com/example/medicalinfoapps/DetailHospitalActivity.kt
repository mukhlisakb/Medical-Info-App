package com.example.medicalinfoapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.medicalinfoapps.databinding.ActivityDetailHospitalBinding

class DetailHospitalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHospitalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hospitalName = intent.getStringExtra("hospital_name")
        val hospitalAddress = intent.getStringExtra("hospital_address")
        val hospitalPhone = intent.getStringExtra("hospital_phone")

        // Display data
        binding.tvNamaRumahSakit.text = hospitalName
        binding.tvAlamatRumahSakit.text = hospitalAddress
        binding.tvNomerHp.text = hospitalPhone

        initView()

    }

    private fun initView() {
        with(binding) {
            btnBack.setOnClickListener{
                onBackPressed()
            }
        }
    }
}