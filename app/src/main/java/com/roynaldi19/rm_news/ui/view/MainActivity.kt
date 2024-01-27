package com.roynaldi19.rm_news.ui.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.roynaldi19.rm_news.databinding.ActivityMainBinding
import com.roynaldi19.rm_news.ui.adapter.EverythingAdapter
import com.roynaldi19.rm_news.ui.adapter.TopHeadlineAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var topHeadlineAdapter: TopHeadlineAdapter
    private lateinit var everythingAdapter: EverythingAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        topHeadlineAdapter = TopHeadlineAdapter()
        val topHeadLineLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        everythingAdapter = EverythingAdapter()
        val everythingLayoutManager = LinearLayoutManager(this)

        binding.rvTopHeadline.layoutManager = topHeadLineLayoutManager
        val topHeadLineItemDecoration =
            DividerItemDecoration(this, topHeadLineLayoutManager.orientation)
        binding.rvTopHeadline.addItemDecoration(topHeadLineItemDecoration)
        binding.rvTopHeadline.adapter = topHeadlineAdapter

        binding.rvEverything.layoutManager = everythingLayoutManager
        val everyThingItemDecoration =
            DividerItemDecoration(this, everythingLayoutManager.orientation)
        binding.rvEverything.addItemDecoration(everyThingItemDecoration)
        binding.rvEverything.adapter = everythingAdapter

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvTopHeadline)

        val country = "id"
        val apiKey = "e290ba5e72814fc78a033491a6ab2363"
        val query = "sepakbola"

        viewModel.topHeadlineArticles.observe(this, Observer {
            topHeadlineAdapter.submitList(it)
        })

        viewModel.loadingTopHeadline.observe(this, Observer {
            binding.pbTopHeadline.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.everythingArticles.observe(this, Observer {
            everythingAdapter.submitList(it)
        })

        viewModel.loadingEverything.observe(this, Observer {
            binding.pbEverything.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.getTopHeadline(country, apiKey)
        viewModel.getEverything(query, apiKey)
    }
}