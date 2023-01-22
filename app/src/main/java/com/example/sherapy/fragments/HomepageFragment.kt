package com.example.sherapy.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sherapy.R
import com.example.sherapy.activities.MainActivity
import com.example.sherapy.activities.SignInActivity
import com.example.sherapy.databinding.ActivityMainBinding
import com.example.sherapy.databinding.FragmentHomepageBinding
import com.example.sherapy.utilities.Constants
import com.example.sherapy.utilities.NewsAdapter
import com.example.sherapy.utilities.OnNewsClickListener
import com.example.sherapy.utilities.PreferenceManager
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class HomepageFragment : Fragment() {

    private val TAG = "FCM"

    private lateinit var binding: FragmentHomepageBinding
    private lateinit var preferenceManager: PreferenceManager
    private val newsApiClient = NewsApiClient("abdd96cad11947489f4a80ae0bb9a1a3")
    val newsItem = arrayListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomepageBinding.inflate(layoutInflater)
        preferenceManager = PreferenceManager(requireContext())

        binding.imageProfile.setOnClickListener{
            findNavController().navigate(HomepageFragmentDirections.actionHomepageFragmentToProfileFragment())
        }

        binding.showmore.setOnClickListener{
            findNavController().navigate(HomepageFragmentDirections.actionHomepageFragmentToArticleFragment())
        }

        binding.imageNotification.setOnClickListener{
            findNavController().navigate(HomepageFragmentDirections.actionHomepageFragmentToNotification())
        }
        binding.appointment1.setOnClickListener {
            findNavController().navigate(HomepageFragmentDirections.actionHomepageFragmentToFragment1Fragment())
        }
        loadUserDetails()
        fetchNews()
//        getToken()
//        setListeners()

//        testUser()
        return binding.root
    }

    private fun testUser() {
        val database = FirebaseFirestore.getInstance()
        Log.d(TAG, Constants.KEY_COLLECTION_USERS)
        Log.d(TAG, preferenceManager.getString(Constants.KEY_USER_ID).toString())
        val documentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(preferenceManager.getString(Constants.KEY_USER_ID)!!)
//        Log.d(TAG, documentReference.toString())
    }

    private fun loadUserDetails() {
        binding.textName.text = preferenceManager.getString(Constants.KEY_NAME)
        binding.imageProfile

//        val bytes: ByteArray = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT)
//        val bitmap: Bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
//        binding.imageProfile.setImageBitmap(bitmap)
    }

    private fun fetchNews() {
        newsApiClient.getEverything(
            EverythingRequest.Builder()
                .q("Mental Health OR Depression OR Anxiety OR anxiety OR depression")
                .language("id")
                .build(),
            object: NewsApiClient.ArticlesResponseCallback {
                override fun onSuccess(response: ArticleResponse?) {
                    response?.articles?.forEach {
                        newsItem.add(it)
                    }
                    bindNews()
                }

                override fun onFailure(throwable: Throwable?) {
                    throwable?.message?.let {
                        Log.e("Error", it)
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                }
            }
        )
    }

    private fun bindNews() {
        val adapter = NewsAdapter(newsItem, OnNewsClickListener {
            val uri = Uri.parse(it)
            startActivity(Intent(Intent.ACTION_VIEW, uri))
        })
        binding.rvBerita2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvBerita2.adapter = adapter
    }

//    private fun getToken() {
//        FirebaseMessaging.getInstance().token.addOnSuccessListener(this::updateToken)
//    }
//
//    private fun updateToken(token: String) {
//        val database = FirebaseFirestore.getInstance()
//        val documentReference =
//            database.collection(Constants.KEY_COLLECTION_USERS).document(preferenceManager.getString(Constants.KEY_USER_ID)!!)
//        documentReference.update(Constants.KEY_FCM_TOKEN, token)
//            .addOnSuccessListener {
//                Log.d(TAG, "Token Berhasil Di Update")
////                showToast("Token Updated Successfully")
//            }
//            .addOnFailureListener {
//                Log.d(TAG, "Token GAGAL Di Update")
////                showToast("Unable to update token")
//            }
//    }

//    private fun signOut() {
//        showToast("Sign out ...")
//        val database: FirebaseFirestore = FirebaseFirestore.getInstance()
//        val documentReference: DocumentReference = database.collection(Constants.KEY_COLLECTION_USERS).document(
//            preferenceManager.getString(Constants.KEY_USER_ID)!!
//        )
//
//        val updates = hashMapOf(
//            Constants.KEY_FCM_TOKEN to FieldValue.delete()
//        )
//        documentReference.update(updates as Map<String, Any>)
//            .addOnSuccessListener { unused ->
//                preferenceManager.clear()
//                startActivity(Intent(applicationContext, SignInActivity::class.java))
//                finish()
//            }
//            .addOnFailureListener { e -> showToast("Unable to sign out") }
//    }

    private fun showToast(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_LONG).show()
    }

}