package com.example.sherapy.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityKonselingBinding
import com.example.sherapy.databinding.Fragment4Binding
import com.example.sherapy.utilities.PreferenceManager
import com.google.firebase.auth.FirebaseAuth

class KonselingActivity : Fragment() {

    private lateinit var _binding: ActivityKonselingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityKonselingBinding.inflate(layoutInflater, container, false)

        _binding.appointment1.setOnClickListener {
            findNavController().navigate(KonselingActivityDirections.actionKonselingFragmentToKonselDetailActivity())
        }
        _binding.appointment2.setOnClickListener {
            findNavController().navigate(KonselingActivityDirections.actionKonselingFragmentToKonselDetailActivity())
            val intent = Intent(requireContext(), KonselDetailActivity::class.java)
            startActivity(intent)
        }
        _binding.appointment3.setOnClickListener {
            findNavController().navigate(KonselingActivityDirections.actionKonselingFragmentToKonselDetailActivity())
        }

        return _binding.root
    }
}