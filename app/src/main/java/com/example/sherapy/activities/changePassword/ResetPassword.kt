package com.example.sherapy.activities.changePassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityResetPasswordBinding

class ResetPassword : AppCompatActivity() {
    private lateinit var binding : ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.openEmail.setOnClickListener {
//            startActivity(Intent(applicationContext, ResetPassword::class.java))
//        }

        binding.tryDiffEmail.setOnClickListener {
            startActivity(Intent(applicationContext, ForgotPassword::class.java))
        }

        binding.backBtn.setOnClickListener {
            startActivity(Intent(applicationContext, ForgotPassword::class.java))
        }

        binding.openEmail.setOnClickListener {
            val launchNext = Intent(this, verificationCode::class.java)
            startActivityForResult(launchNext, 2404)
            val launchIntent = packageManager.getLaunchIntentForPackage("com.google.android.gm")
            startActivity(launchIntent)
        }

    }
}