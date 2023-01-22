package com.example.sherapy.activities.changePassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityVerificationCodeBinding

class verificationCode : AppCompatActivity() {
    private lateinit var binding : ActivityVerificationCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        binding = ActivityVerificationCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            startActivity(Intent(applicationContext, ResetPassword::class.java))
        }

        binding.submit.setOnClickListener {
            startActivity(Intent(applicationContext, CreateNewPassword::class.java))
        }
    }
}