package com.example.medicalinfoapps.main

import androidx.recyclerview.widget.DiffUtil
import com.example.medicalinfoapps.common.MedicalInfo

class DiffUtilCallBack: DiffUtil.ItemCallback<MedicalInfo>() {
    override fun areItemsTheSame(oldItem: MedicalInfo, newItem: MedicalInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MedicalInfo, newItem: MedicalInfo): Boolean {
        return oldItem == newItem
    }
}