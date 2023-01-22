package com.example.sherapy.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.Window
import android.widget.Toast
import com.example.sherapy.activities.changePassword.ForgotPassword
import com.example.sherapy.databinding.ActivitySignInBinding
import com.example.sherapy.databinding.ActivitySignPhoneBinding
import com.example.sherapy.utilities.Constants
import com.example.sherapy.utilities.PreferenceManager
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInTelfActivity : AppCompatActivity() {
//    lateinit var binding: ActivitySignInBinding
//    private lateinit var firebaseAuth: FirebaseAuth
//    private lateinit var googleSignInClient: GoogleSignInClient
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        supportActionBar?.hide()
//        binding = ActivitySignInBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//        binding.textCreateNewAccount.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//        }
//        binding.buttonSignIn.setOnClickListener {
//            val email = binding.inputEmail.text.toString()
//            val pass = binding.inputPassword.text.toString()
//
//            if (email.isNotEmpty() && pass.isNotEmpty()) {
//                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        val intent = Intent(this, MainActivity::class.java)
//                        startActivity(intent)
//                    } else {
//                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
//
//            }
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (firebaseAuth.currentUser != null) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }

    private lateinit var binding: ActivitySignPhoneBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_in)  //ORI

        preferenceManager = PreferenceManager(applicationContext)
        if ( preferenceManager.getBoolean(Constants.KEY_IS_SIGNED_IN) ) {
            val intent: Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding = ActivitySignPhoneBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnemail.setOnClickListener {
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }
        binding.textCreateNewAccount.setOnClickListener {
            startActivity(Intent(applicationContext, SignUpActivity::class.java))
        }
        binding.buttonSignIn.setOnClickListener {
            if ( isValidSignInDetails() ) {
                signIn()
            }
        }
        binding.forgotPassword.setOnClickListener {
            startActivity(Intent(applicationContext, ForgotPassword::class.java))
        }
    }

    private fun signIn() {
        loading(true)
        val database = FirebaseFirestore.getInstance()

        database.collection(Constants.KEY_COLLECTION_USERS)
            .whereEqualTo(Constants.KEY_TELEPHONE, binding.inputPhone.text.toString())
            .whereEqualTo( Constants.KEY_PASSWORD, binding.inputPassword.text.toString() )
            .get()
            .addOnSuccessListener { task ->
                if ( !task.isEmpty ) {
                    val documentSnapshot: DocumentSnapshot = task.documents.get(0)
                    if (binding.checkbox.isChecked) {
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                    } else {
                        preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, false)
                    }
                    preferenceManager.putString(Constants.KEY_USER_ID, documentSnapshot.id)
                    documentSnapshot.getString(Constants.KEY_NAME)?.let { preferenceManager.putString(Constants.KEY_NAME, it) }
                    documentSnapshot.getString(Constants.KEY_IMAGE)?.let { preferenceManager.putString(Constants.KEY_USER_ID, it) }
                    documentSnapshot.getString(Constants.KEY_TELEPHONE)?.let { preferenceManager.putString(Constants.KEY_TELEPHONE, it) }

                    val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                } else {
                    loading(false)
                    showToast("Unable to Sign In")
                }
            }.addOnFailureListener { exception ->
                loading(false)
                exception.message?.let { showToast(it) }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun isValidSignInDetails(): Boolean {
        if ( binding.inputPhone.text.toString().trim().isEmpty() ) {
            showToast("Enter Telephone")
            return false
        } else if ( !Patterns.PHONE.matcher(binding.inputPhone.text.toString()).matches() ) {
            showToast("Enter Valid Image")
            return false
        } else if ( binding.inputPassword.text.toString().trim().isEmpty() ) {
            showToast("Enter Password")
            return false
        } else {
            return true
        }
    }

    private fun loading(isLoading: Boolean) {
        if (isLoading) {
            binding.buttonSignIn.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.buttonSignIn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}