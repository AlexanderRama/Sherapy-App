package com.example.sherapy.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sherapy.R
import com.example.sherapy.databinding.FragmentArticleBinding
import com.example.sherapy.utilities.NewsAdapter
import com.example.sherapy.utilities.OnNewsClickListener
import com.kwabenaberko.newsapilib.NewsApiClient
import com.kwabenaberko.newsapilib.models.Article
import com.kwabenaberko.newsapilib.models.request.EverythingRequest
import com.kwabenaberko.newsapilib.models.response.ArticleResponse

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val newsApiClient = NewsApiClient("abdd96cad11947489f4a80ae0bb9a1a3")
    val newsItem = arrayListOf<Article>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArticleBinding.inflate(layoutInflater)
        binding.imageButton.setOnClickListener {
            findNavController().navigate(ArticleFragmentDirections.actionArticleFragmentToHomepageFragment())
        }

        fetchNews()

        return binding.root
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
        binding.rvBerita.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvBerita.adapter = adapter
    }
}