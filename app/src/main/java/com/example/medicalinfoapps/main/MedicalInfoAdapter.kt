package com.example.medicalinfoapps.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalinfoapps.DetailHospitalActivity
import com.example.medicalinfoapps.R
import com.example.medicalinfoapps.common.MedicalInfo

class MedicalInfoAdapter(val listener: MedicalInfoListener): ListAdapter<MedicalInfo, MedicalInfoAdapter.MedicalInfoViewHolder>(DiffUtilCallBack()) {

    inner class MedicalInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: MedicalInfo) {
            val hospitalName = itemView.findViewById<TextView>(R.id.tv_hospital_name)
            val hospitalAddress = itemView.findViewById<TextView>(R.id.tv_hospital_address)
            val hospitalPhone = itemView.findViewById<TextView>(R.id.tv_hospital_phone)

            hospitalName.text = item.hospitalName
            hospitalAddress.text = item.hospitalAddress
            hospitalPhone.text = item.hospitalPhone

            // Pindah ke DetailHospitalActivity saat item diklik
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailHospitalActivity::class.java).apply {
                    putExtra("hospital_name", item.hospitalName)
                    putExtra("hospital_address", item.hospitalAddress)
                    putExtra("hospital_phone", item.hospitalPhone)
                }
                context.startActivity(intent)
            }

            // Handle klik pada nomor telepon
            hospitalPhone.setOnClickListener {
                listener.onNumberClicked(hospitalPhone.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicalInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_medical_info, parent, false)
        return MedicalInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedicalInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(itemList: List<MedicalInfo>) {
        submitList(itemList)
    }

    fun addData(newItems: MedicalInfo) {
        submitList(currentList + newItems)
    }
}
