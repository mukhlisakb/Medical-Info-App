package com.example.medicalinfoapps.common

import android.os.Parcelable
import com.example.medicalinfoapps.R
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class MedicalInfo(
    val id: String = UUID.randomUUID().toString(),
    val photo: Int = R.drawable.rs_kota,
    val hospitalName: String = "Hospital",
    val hospitalAddress: String = "Jl. Selat Panjang, Pontianak Utara",
    val hospitalPhone: String = "+62 812-8691-0411 ",
): Parcelable

