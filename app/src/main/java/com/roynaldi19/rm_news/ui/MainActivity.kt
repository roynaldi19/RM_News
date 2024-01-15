package com.roynaldi19.rm_news.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.roynaldi19.rm_news.R
import com.roynaldi19.rm_news.data.retrofit.ApiConfig
import com.roynaldi19.rm_news.data.retrofit.ApiService
import com.roynaldi19.rm_news.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var topHeadlineAdapter: TopHeadlineAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        topHeadlineAdapter = TopHeadlineAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        binding.rvTopHeadline.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvTopHeadline.addItemDecoration(itemDecoration)
        binding.rvTopHeadline.adapter = topHeadlineAdapter

        val country = "id"
        val apiKey = "e290ba5e72814fc78a033491a6ab2363"

        showTopHeadline(country, apiKey)
    }

    private fun showTopHeadline(country : String, apiKey: String) {
        lifecycleScope.launch {
            try {
                val apiService = ApiConfig.getApiService()
                val response = apiService.getTopHeadLines(country, apiKey)

                if (response.isSuccessful) {
                    val articles = response.body()?.articles

                    if (articles != null) {
                        topHeadlineAdapter.submitList(articles)
                    }
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                }
            } catch (e: Exception){
                Log.e("MainActivity", "Exception: ${e.message}")

            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}