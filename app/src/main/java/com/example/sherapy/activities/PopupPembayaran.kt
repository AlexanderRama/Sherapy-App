package com.example.sherapy.activities

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.databinding.ActivityPembayaranBinding
import com.example.sherapy.databinding.PopupPembayaranBinding
import com.example.sherapy.utilities.PreferenceManager

class PopupPembayaran : DialogFragment() {

    private lateinit var binding: PopupPembayaranBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PopupPembayaranBinding.inflate(layoutInflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.closeBtn.setOnClickListener {
            findNavController().navigate(PopupPembayaranDirections.actionPopupPembayaranToPembayaranActivity())
        }
        return binding.root
    }
}