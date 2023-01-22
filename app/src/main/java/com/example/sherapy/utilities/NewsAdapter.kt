package com.example.sherapy.utilities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sherapy.databinding.ItemNewsLayoutBinding
import com.kwabenaberko.newsapilib.models.Article
import org.joda.time.DateTime
import kotlin.random.Random

class NewsAdapter(private val newsList: List<Article>, private val onNewsClickListener: OnNewsClickListener): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(news: Article) {
            
            binding.apply {
                tvNewsTitle.text = news.title
                val dt = DateTime(news.publishedAt)
                val newsDate = "${dt.dayOfMonth}-${dt.monthOfYear}-${dt.year}"
                tvPublishTime.text = newsDate
                tvViewsCounter.text = Random.nextInt(1000).toString()
                binding.tvNewsTitle.isSelected = true
                binding.tvPublishTime.isSelected = true
                binding.tvViewsCounter.isSelected = true
                Glide.with(binding.root)
                    .load(news.urlToImage)
                    .centerCrop()
                    .into(binding.ivNews)

                containerNews.setOnClickListener {
                    onNewsClickListener.clickListener(news.url)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}

class OnNewsClickListener(val clickListener: (url: String) -> Unit) {
    fun onClick(url: String) = clickListener(url)
}