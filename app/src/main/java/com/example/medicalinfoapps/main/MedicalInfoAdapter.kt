package com.example.medicalinfoapps.main

import androidx.recyclerview.widget.ListAdapter
import com.example.medicalinfoapps.common.MedicalInfo

class MedicalInfoAdapter: ListAdapter<MedicalInfo, MedicalInfoViewHolder>(DiffUtilCallBack()) {
}