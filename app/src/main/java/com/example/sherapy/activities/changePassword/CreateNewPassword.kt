package com.example.sherapy.activities.changePassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sherapy.R
import com.example.sherapy.activities.SignInActivity
import com.example.sherapy.databinding.ActivityCreateNewPasswordBinding

class CreateNewPassword : AppCompatActivity() {
    private lateinit var binding : ActivityCreateNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_password)

        binding = ActivityCreateNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closeBtn.setOnClickListener {
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }

        binding.resetPassword.setOnClickListener {
            startActivityForResult(Intent(applicationContext, SignInActivity::class.java), 2404)
        }
    }
}