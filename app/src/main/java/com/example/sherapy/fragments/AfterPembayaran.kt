package com.example.sherapy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.activities.QuestionModel
import com.example.sherapy.databinding.Fragment2Binding
import com.example.sherapy.databinding.KonselsuccessBinding
import kotlinx.android.synthetic.main.konselsuccess.*

class AfterPembayaran : Fragment() {

    private lateinit var binding: KonselsuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = KonselsuccessBinding.inflate(layoutInflater, container, false)

        binding.btnChat.setOnClickListener{
            findNavController().navigate(AfterPembayaranDirections.actionAfterPembayaranToChatFragment())
        }
        binding.imageButton.setOnClickListener {
            findNavController().navigate(AfterPembayaranDirections.actionAfterPembayaranToHomepageFragment())
        }
        return binding.root
    }
}