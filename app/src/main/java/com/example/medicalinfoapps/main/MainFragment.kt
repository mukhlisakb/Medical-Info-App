package com.example.medicalinfoapps.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.medicalinfoapps.R
import com.example.medicalinfoapps.common.MedicalInfo
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val rvMedicalInfo by lazy { view?.findViewById<RecyclerView>(R.id.rv_medical_info) }
    private val febAddData by lazy { view?.findViewById<FloatingActionButton>(R.id.feb_add_data) }
    private val adapter: MedicalInfoAdapter by lazy { MedicalInfoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMedicalInfoList()
        febAddData?.setOnClickListener {
            val bundle = bundleOf("medicalInfoData" to adapter.currentList.toTypedArray())
            findNavController().navigate(R.id.action_mainFragment_to_inputFragment, bundle)
        }

    }

    private fun initMedicalInfoList() {
        rvMedicalInfo?.layoutManager = LinearLayoutManager(context)
        rvMedicalInfo?.adapter = adapter
        adapter.setData(generateDummyData())
    }

    private fun generateDummyData(): List<MedicalInfo> {
        return listOf(
            MedicalInfo(hospitalName = "Hospital 1"),
            MedicalInfo(hospitalName = "Hospital 2"),
        )
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}