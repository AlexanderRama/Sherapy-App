package com.example.sherapy.activities

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityBayarsuccessBinding
import com.example.sherapy.databinding.ActivityPembayaranBinding
import com.example.sherapy.utilities.PreferenceManager

class BayarSuccessActivity : Fragment() {

    private lateinit var binding: ActivityBayarsuccessBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityBayarsuccessBinding.inflate(layoutInflater, container, false)

        binding.appCompatButton.setOnClickListener {
            findNavController().navigate(BayarSuccessActivityDirections.actionBayarSuccessActivityToHomepageFragment())
        }
        binding.hubkonselor.setOnClickListener {
            findNavController().navigate(BayarSuccessActivityDirections.actionBayarSuccessActivityToAfterPembayaran())
        }
        return binding.root
    }
}