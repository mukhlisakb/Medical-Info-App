package com.example.medicalinfoapps.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.medicalinfoapps.R
import com.example.medicalinfoapps.about.AboutActivity
import com.example.medicalinfoapps.common.MedicalInfo
import com.example.medicalinfoapps.databinding.FragmentMainBinding

class MainFragment : Fragment(), MedicalInfoListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MedicalInfoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout using View Binding
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initMedicalInfoList()
        getDataFromInputFragment()

        binding.febAddData.setOnClickListener {
            val bundle = bundleOf("medicalInfoData" to adapter.currentList.toTypedArray())
            findNavController().navigate(R.id.action_mainFragment_to_inputFragment, bundle)
        }
    }

    private fun initViews() {
        binding.febProfile.setOnClickListener {
            val intent = Intent(requireContext(), AboutActivity::class.java)
            startActivity(intent)
        }
        adapter = MedicalInfoAdapter(this)
    }

    private fun initMedicalInfoList() {
        binding.rvMedicalInfo.layoutManager = LinearLayoutManager(context)
        binding.rvMedicalInfo.adapter = adapter
        adapter.setData(generateDummyData())
    }

    private fun getDataFromInputFragment() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<List<MedicalInfo>>("resultKey")
            ?.observe(viewLifecycleOwner) { result ->
                adapter.setData(result)
            }
    }

    private fun generateDummyData(): List<MedicalInfo> {
        return listOf(
            MedicalInfo(hospitalName = "Hospital 1"),
            MedicalInfo(hospitalName = "Hospital 2"),
            MedicalInfo(hospitalName = "Hospital 3"),
            MedicalInfo(hospitalName = "Hospital 4"),
            MedicalInfo(hospitalName = "Hospital 5"),
            MedicalInfo(hospitalName = "Hospital 6"),
            MedicalInfo(hospitalName = "Hospital 7"),
            MedicalInfo(hospitalName = "Hospital 8"),
            MedicalInfo(hospitalName = "Hospital 9"),
            MedicalInfo(hospitalName = "Hospital 10")
        )
    }

    override fun onNumberClicked(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // To avoid memory leaks
    }
}
