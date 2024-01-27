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

class TopHeadlineAdapter :
    ListAdapter<ArticlesItem, TopHeadlineAdapter.MyViewHolder>(DIFF_CALLBACK) {
    class MyViewHolder(val binding: ItemEverythingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticlesItem) {
            binding.tvJudulBerita.text = article.title
            binding.tvSumber.text = article.author
            binding.tvTanggal.text = article.publishedAt

            if (article.urlToImage != null) {
                Glide.with(binding.root.context)
                    .load(article.urlToImage).placeholder(R.drawable.headline)
                    .error(R.drawable.error).into(binding.ivTopHeadline)
            } else {
                binding.ivTopHeadline.setImageResource(R.drawable.headline)
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