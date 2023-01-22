package com.example.sherapy.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sherapy.R
import com.example.sherapy.databinding.ActivityEditProfileBinding
import com.example.sherapy.utilities.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditProfileActivity : AppCompatActivity() {

    private lateinit var preferenceManager: PreferenceManager
    private lateinit var binding: ActivityEditProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferenceManager = PreferenceManager(applicationContext)

        binding.etValueNama.setText(preferenceManager.getString(Constants.KEY_NAME))
        binding.etValueEmail.setText(preferenceManager.getString(Constants.KEY_EMAIL))
        loadUserDetails()

        binding.apply {
            ivEditNama.setOnClickListener {
                etValueNama.visibility = View.VISIBLE
                tvValueNama.visibility = View.INVISIBLE
                ivEditNama.setImageResource(R.drawable.calendar)

                ivEditNama.setOnClickListener {
                    saveData()
                    ivEditNama.setImageResource(R.drawable.ic_baseline_edit_24)

                    tvValueNama.text = etValueNama.text
                    etValueNama.visibility = View.INVISIBLE
                    tvValueNama.visibility = View.VISIBLE
                }
            }
            ivEditEmail.setOnClickListener {
                etValueEmail.visibility = View.VISIBLE
                tvValueEmail.visibility = View.INVISIBLE
                ivEditEmail.setImageResource(R.drawable.calendar)

                ivEditEmail.setOnClickListener {
                    saveData()
                    ivEditEmail.setImageResource(R.drawable.ic_baseline_edit_24)

                    tvValueEmail.text = etValueEmail.text
                    etValueEmail.visibility = View.INVISIBLE
                    tvValueEmail.visibility = View.VISIBLE
                }
            }
        }

    }

    fun saveData() {
        val database = Firebase.firestore

        val user = hashMapOf(
//            Constants.ID to ("1" + randomUUID().toString()),
            Constants.KEY_NAME to binding.etValueNama.text.toString(),
            Constants.KEY_EMAIL to binding.etValueEmail.text.toString(),
            //Constants.KEY_PASSWORD to binding.inputPassword.text.toString(),
            //Constants.KEY_IMAGE to encodedImage
        )

        database.collection(Constants.KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener { documentReference ->


                preferenceManager.putString(Constants.KEY_NAME, binding.etValueNama.text.toString())
                preferenceManager.putString(Constants.KEY_EMAIL, binding.etValueEmail.text.toString())

                val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                exception.message?.let { Toast.makeText(this, it, Toast.LENGTH_LONG).show() }
            }
    }
    private fun loadUserDetails() {
        binding.includeProfileCard.tvUsername.text = preferenceManager.getString(Constants.KEY_NAME)
        binding.includeProfileCard.tvEmail.text = preferenceManager.getString((Constants.KEY_EMAIL))
    }

}