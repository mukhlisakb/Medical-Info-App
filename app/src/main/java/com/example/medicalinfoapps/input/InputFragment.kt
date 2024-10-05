package com.example.medicalinfoapps.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.example.medicalinfoapps.R
import com.example.medicalinfoapps.common.MedicalInfo
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class InputFragment : Fragment() {
    private val btnBack by lazy { view?.findViewById<ImageView>(R.id.iv_back_arrow) }
    private val inputNameField by lazy { view?.findViewById<TextInputEditText>(R.id.input_hospital_name) }
    private val inputAddressField by lazy { view?.findViewById<TextInputEditText>(R.id.input_address_name) }
    private val inputPhoneField by lazy { view?.findViewById<TextInputEditText>(R.id.input_phone_name) }
    private val btnSave by lazy { view?.findViewById<MaterialButton>(R.id.btn_save) }
    private val btnDiscard by lazy { view?.findViewById<MaterialButton>(R.id.btn_discard) }
    private val medicalInfoData = mutableListOf<MedicalInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        medicalInfoData.addAll(
            arguments?.getParcelableArray(
                "medicalInfoData")?.toMutableList() as MutableList<MedicalInfo>
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_input, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDiscard?.setOnClickListener {
            discardData()
        }
        btnSave?.setOnClickListener {
            if (isEntryValid()) {
                saveData()
            } else {
                showToast("Please fill in all fields")
            }
        }
        handleBackPress()

        btnBack?.setOnClickListener {
            backtoMainFragment()
        }
    }

    private fun handleBackPress() {
        val callBack = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                backtoMainFragment()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callBack)
    }

    private fun discardData() {
        inputNameField?.setText("")
        inputAddressField?.setText("")
        inputPhoneField?.setText("")
    }

    private fun saveData() {
        val newDataEntry = MedicalInfo(
            hospitalName = inputNameField?.text.toString(),
            hospitalAddress = inputAddressField?.text.toString(),
            hospitalPhone = inputPhoneField?.text.toString()
        )
        try {
            medicalInfoData.add(newDataEntry)
            discardData()
            showToast("Data saved successfully")
        } catch (e: Exception) {
            showToast("Error saving data")
        }
    }

    private fun isEntryValid(): Boolean {
        return !(inputNameField?.text.toString().isBlank() ||
                inputAddressField?.text.toString().isBlank() ||
                inputPhoneField?.text.toString().isBlank())
    }

    private fun backtoMainFragment() {
        val resultData = medicalInfoData
        findNavController().previousBackStackEntry?.savedStateHandle?.set("resultKey", resultData)
        findNavController().navigateUp()

    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

}
