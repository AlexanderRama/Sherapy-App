package com.example.sherapy.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.sherapy.databinding.ActivityNotifikasiBinding
import com.example.sherapy.databinding.KonselsuccessBinding
import com.example.sherapy.fragments.AfterPembayaranDirections

class Notification : Fragment() {

    private lateinit var binding: ActivityNotifikasiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ActivityNotifikasiBinding.inflate(layoutInflater, container, false)

        binding.btn1.setOnClickListener{
            findNavController().navigate(NotificationDirections.actionNotificationToBayarSuccessActivity())
        }
        binding.btn2.setOnClickListener{
            findNavController().navigate(NotificationDirections.actionNotificationToBayarSuccessActivity())
        }
        binding.btn3.setOnClickListener{
            findNavController().navigate(NotificationDirections.actionNotificationToBayarSuccessActivity())
        }
        binding.btn4.setOnClickListener{
            findNavController().navigate(NotificationDirections.actionNotificationToBayarSuccessActivity())
        }
        binding.btn5.setOnClickListener{
            findNavController().navigate(NotificationDirections.actionNotificationToBayarSuccessActivity())
        }
        binding.imageButton.setOnClickListener {
            findNavController().navigate(NotificationDirections.actionNotificationToHomepageFragment())
        }
        return binding.root
    }
}