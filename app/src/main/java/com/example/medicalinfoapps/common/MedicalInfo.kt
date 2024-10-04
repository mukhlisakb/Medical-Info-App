package com.example.medicalinfoapps.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class MedicalInfo(
    val id: String = UUID.randomUUID().toString(),
    val hospitalName: String = "Hospital",
    val hospitalAddress: String = "Jl. Selat Panjang, Pontianak Utara",
    val hospitalPhone: String = "+62 812-8691-0411 ",
): Parcelable

