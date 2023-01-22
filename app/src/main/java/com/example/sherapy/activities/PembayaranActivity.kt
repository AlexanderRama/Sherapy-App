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
import com.example.sherapy.databinding.ActivityPembayaranBinding
import com.example.sherapy.databinding.PopupPembayaranBinding
import com.example.sherapy.utilities.PreferenceManager
import kotlinx.android.synthetic.main.activity_reset_password.*

class PembayaranActivity : Fragment() {

    private lateinit var binding: ActivityPembayaranBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityPembayaranBinding.inflate(layoutInflater, container, false)

        binding.bayar.setOnClickListener {
            findNavController().navigate(PembayaranActivityDirections.actionPembayaranActivityToBayarSuccessActivity())
        }

        binding.upvote.setOnClickListener {
            findNavController().navigate(PembayaranActivityDirections.actionPembayaranActivityToPopupPembayaran())
        }
        binding.btn1.setOnClickListener{
            binding.btn1.isSelected = !binding.btn1.isSelected
            if(binding.btn1.isSelected) {
                binding.text1.setTextColor(resources.getColor(R.color.white))
                binding.text2.setTextColor(resources.getColor(R.color.white))
            }
           else {
                binding.text1.setTextColor(resources.getColor(R.color.black))
                binding.text2.setTextColor(resources.getColor(R.color.black))
            }
        }
        binding.btn2.setOnClickListener{
            binding.btn2.isSelected = !binding.btn2.isSelected
        }
        binding.btn3.setOnClickListener{
            binding.btn3.isSelected = !binding.btn3.isSelected
        }
        binding.btn4.setOnClickListener{
            binding.btn4.isSelected = !binding.btn4.isSelected
        }
        binding.btn5.setOnClickListener{
            binding.btn5.isSelected = !binding.btn5.isSelected
        }
        binding.btn6.setOnClickListener{
            binding.btn6.isSelected = !binding.btn6.isSelected
        }
        binding.btn7.setOnClickListener{
            binding.btn7.isSelected = !binding.btn7.isSelected
        }
        binding.radio1.setOnClickListener{
            binding.radio1.isSelected = !binding.radio1.isSelected
        }
        binding.radio2.setOnClickListener{
            binding.radio2.isSelected = !binding.radio2.isSelected
        }
        return binding.root
    }
}