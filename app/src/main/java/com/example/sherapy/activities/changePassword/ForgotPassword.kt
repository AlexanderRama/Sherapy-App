package com.example.sherapy.activities.changePassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sherapy.R
import com.example.sherapy.activities.SignInActivity
import com.example.sherapy.databinding.ActivityForgotPasswordBinding

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding : ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resetPassword.setOnClickListener {
            startActivity(Intent(applicationContext, ResetPassword::class.java))
        }

        binding.closeBtn.setOnClickListener {
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }
    }
}