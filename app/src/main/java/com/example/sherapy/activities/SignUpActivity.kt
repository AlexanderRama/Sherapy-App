package com.example.sherapy.activities

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.sherapy.databinding.ActivitySignUpBinding
import com.example.sherapy.utilities.Constants
import com.example.sherapy.utilities.PreferenceManager
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var encodedImage: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)  //ORI
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(applicationContext)

        setListeners()
    }

    private fun setListeners() {
        binding.textSignIn.setOnClickListener { onBackPressed() }
        binding.buttonSignUp.setOnClickListener {
            if ( isValidSignUpDetails() ) {
                signUp()
            }
        }
        binding.layoutImage.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            pickImage.launch(intent)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun signUp() {
        loading(true)
        val database = FirebaseFirestore.getInstance()

        val user = hashMapOf(
//            Constants.ID to ("1" + randomUUID().toString()),
            Constants.KEY_NAME to binding.inputName.text.toString(),
            Constants.KEY_EMAIL to binding.inputEmail.text.toString(),
            Constants.KEY_PASSWORD to binding.inputPassword.text.toString(),
            Constants.KEY_TELEPHONE to binding.inputTelf.text.toString(),
            Constants.KEY_IMAGE to encodedImage
        )

        database.collection(Constants.KEY_COLLECTION_USERS)
            .add(user)
            .addOnSuccessListener { documentReference ->
                loading(false)
                preferenceManager.putBoolean(Constants.KEY_IS_SIGNED_IN, true)
                preferenceManager.putString(Constants.KEY_USER_ID, documentReference.id)
                preferenceManager.putString(Constants.KEY_TELEPHONE, binding.inputTelf.text.toString())
                preferenceManager.putString(Constants.KEY_NAME, binding.inputName.text.toString())
                preferenceManager.putString(Constants.KEY_IMAGE, encodedImage)

                val intent: Intent = Intent(applicationContext, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
            .addOnFailureListener { exception ->
                loading(false)
                exception.message?.let { showToast(it) }
            }
    }

    private fun encodeImage(bitmap: Bitmap): String {
        val previewWidth:Int = 150
        val previewHeight:Int = bitmap.height * previewWidth / bitmap.width

        val previewBitmap: Bitmap  = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false)
        val byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()

        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream)

        val bytes: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }


    private val pickImage: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            if (result.data != null) {
                val imageUri: Uri? = result.data!!.data
                try {
                    val inputStream: InputStream = contentResolver.openInputStream(imageUri!!)!!
                    val bitmap: Bitmap = BitmapFactory.decodeStream(inputStream)
                    binding.imageProfil.setImageBitmap(bitmap)
                    binding.textAddImage.visibility = View.GONE
                    encodedImage = encodeImage(bitmap)
                } catch ( e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun isValidSignUpDetails(): Boolean {
        if ( binding.inputName.text.toString().trim().isEmpty() ) {
            showToast("Enter Name")
            return false
        } else if ( binding.inputEmail.text.toString().trim().isEmpty() ) {
            showToast("Enter Email")
            return false
        } else if ( binding.inputTelf.text.toString().trim().isEmpty() ) {
            showToast("Enter No Telephone")
            return false
        } else if ( !Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.text.toString()).matches() ) {
            showToast("Enter Valid Email")
            return false
        } else if ( binding.inputPassword.text.toString().trim().isEmpty() ) {
            showToast("Enter Password")
            return false
        } else if ( binding.inputConfirmPassword.text.toString().trim().isEmpty() ) {
            showToast("Confirm New Password")
            return false
        } else if (binding.inputPassword.text.toString() != binding.inputConfirmPassword.text.toString()) {
            showToast("Password & Confirm Password Must Be Same")
            return false
        } else {
            return true
        }
    }

    private fun loading(isLoading: Boolean) {
        if ( isLoading ) {
            binding.buttonSignUp.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.buttonSignUp.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        }
    }
}