package com.example.sherapy.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.databinding.FragmentChatBinding
import com.example.sherapy.databinding.KonselsuccessBinding

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)

        binding.imageButton.setOnClickListener {
            findNavController().navigate(ChatFragmentDirections.actionChatFragmentToAfterPembayaran())
        }
        return binding.root
    }
}