package com.example.medicalinfoapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.medicalinfoapps.databinding.ActivityDetailHospitalBinding

class DetailHospitalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailHospitalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)  // Mengaktifkan tampilan Edge-to-Edge

        binding = ActivityDetailHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hospitalPhoto = intent.getIntExtra("hospital_photo", 0)
        val hospitalName = intent.getStringExtra("hospital_name")
        val hospitalAddress = intent.getStringExtra("hospital_address")
        val hospitalPhone = intent.getStringExtra("hospital_phone")

        // Display data
        // Menggunakan Glide untuk memuat gambar dengan ukuran yang sesuai
        Glide.with(this)
            .load(hospitalPhoto)
            .override(800, 800)  // Menurunkan ukuran gambar menjadi maksimal 800x800 piksel
            .transform(CenterCrop())  // Memotong gambar untuk mengisi ImageView
            .into(binding.imgHospitalPhoto)

        binding.tvNamaRumahSakit.text = hospitalName
        binding.tvAlamatRumahSakit.text = hospitalAddress
        binding.tvNomerHp.text = hospitalPhone

        initView()
    }

    private fun initView() {
        with(binding) {
            btnBack.setOnClickListener {
                onBackPressedDispatcher.onBackPressed()  // Mengganti dengan cara baru untuk back navigation
            }
        }
    }
}
