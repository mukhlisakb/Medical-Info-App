package com.example.medicalinfoapps.main

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medicalinfoapps.DetailHospitalActivity
import com.example.medicalinfoapps.R
import com.example.medicalinfoapps.common.MedicalInfo

class MedicalInfoAdapter(val listener: MedicalInfoListener) : ListAdapter<MedicalInfo, MedicalInfoAdapter.MedicalInfoViewHolder>(DiffUtilCallBack()) {

    inner class MedicalInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MedicalInfo) {
            val hospitalPhoto = itemView.findViewById<ImageView>(R.id.img_hospital_photo)
            val hospitalName = itemView.findViewById<TextView>(R.id.tv_hospital_name)
            val hospitalAddress = itemView.findViewById<TextView>(R.id.tv_hospital_address)
            val hospitalPhone = itemView.findViewById<TextView>(R.id.tv_hospital_phone)

            // Menggunakan Glide untuk memuat gambar dengan ukuran yang disesuaikan
            Glide.with(itemView.context)
                .load(item.photo) // Gambar sumber
                .override(80, 80)  // Ukuran gambar yang diinginkan
                .centerCrop()       // Menjaga rasio gambar dan menghindari pemotongan yang tidak perlu
                .into(hospitalPhoto)

            hospitalName.text = item.hospitalName
            hospitalAddress.text = item.hospitalAddress
            hospitalPhone.text = item.hospitalPhone

            // Pindah ke DetailHospitalActivity saat item diklik
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, DetailHospitalActivity::class.java).apply {
                    putExtra("hospital_photo", item.photo)
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

    // Fungsi untuk mengambil data dari Resources
    fun setDataFromResources(context: Context) {
        val photos = context.resources.obtainTypedArray(R.array.data_photo)
        val names = context.resources.getStringArray(R.array.data_hospital_name)
        val addresses = context.resources.getStringArray(R.array.data_hospital_address)
        val phones = context.resources.getStringArray(R.array.data_hospital_phone)

        val itemList = mutableListOf<MedicalInfo>()
        for (i in names.indices) {
            val photoResId = photos.getResourceId(i, -1)
            val name = names[i]
            val address = addresses[i]
            val phone = phones[i]

            itemList.add(MedicalInfo(photo = photoResId, hospitalName = name, hospitalAddress = address, hospitalPhone = phone))
        }
        photos.recycle()
        setData(itemList)
    }
}