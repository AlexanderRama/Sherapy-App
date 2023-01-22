package com.example.sherapy.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityKonseldetailBinding
import com.example.sherapy.databinding.ActivityKonselingBinding
import com.example.sherapy.utilities.PreferenceManager

class KonselDetailActivity : Fragment() {

    private lateinit var binding: ActivityKonseldetailBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityKonseldetailBinding.inflate(layoutInflater, container, false)

        binding.appointment1.setOnClickListener {
            findNavController().navigate(KonselDetailActivityDirections.actionKonselDetailActivityToPembayaranActivity())
        }
        binding.imageButton.setOnClickListener {
            findNavController().navigate(KonselDetailActivityDirections.actionKonselDetailActivityToKonselingFragment())
        }
        return binding.root
    }
}