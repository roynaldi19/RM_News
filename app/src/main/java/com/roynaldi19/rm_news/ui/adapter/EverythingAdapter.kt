package com.roynaldi19.rm_news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.roynaldi19.rm_news.R
import com.roynaldi19.rm_news.data.response.ArticlesItem
import com.roynaldi19.rm_news.databinding.ItemEverythingBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class EverythingAdapter :
    ListAdapter<ArticlesItem, EverythingAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemEverythingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem) {
            binding.tvJudulBerita.text = article.title
            binding.tvSumber.text = article.author
            val formattedDate = convertDateFormat(article.publishedAt)
            binding.tvTanggal.text = formattedDate

            if (article.urlToImage != null) {
                Glide.with(binding.root.context)
                    .load(article.urlToImage).placeholder(R.drawable.headline)
                    .error(R.drawable.error).into(binding.ivTopHeadline)
            } else {
                binding.ivTopHeadline.setImageResource(R.drawable.headline)
            }
        }

        private fun convertDateFormat(publishedAt: String): String {
            try {
                val originalFormat =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                val targetFormat = SimpleDateFormat("HH:mm  dd-MM-yyyy", Locale.getDefault())
                val date: Date = originalFormat.parse(publishedAt) ?: return publishedAt
                return targetFormat.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
                return publishedAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemEverythingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(
            article
        )
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}