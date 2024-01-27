package com.roynaldi19.rm_news.ui.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roynaldi19.rm_news.data.response.ArticlesItem
import com.roynaldi19.rm_news.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _topHeadlineArticles = MutableLiveData<List<ArticlesItem>>()
    val topHeadlineArticles: LiveData<List<ArticlesItem>> get() = _topHeadlineArticles

    private val _everythingArticles = MutableLiveData<List<ArticlesItem>>()
    val everythingArticles: LiveData<List<ArticlesItem>> get() = _everythingArticles

    private val _loadingTopHeadline = MutableLiveData<Boolean>()
    val loadingTopHeadline: LiveData<Boolean> get() = _loadingTopHeadline

    private val _loadingEverything = MutableLiveData<Boolean>()
    val loadingEverything: LiveData<Boolean> get() = _loadingEverything

    fun getTopHeadline(country: String, apiKey: String) {
        viewModelScope.launch {
            try {
                _loadingTopHeadline.value = true
                val apiService = ApiConfig.getApiService()
                val response = apiService.getTopHeadLines(country, apiKey)

                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    _topHeadlineArticles.value = articles
                    _loadingTopHeadline.value = false
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Exception: ${e.message}")
                _loadingTopHeadline.value = false

            }
        }
    }

    fun getEverything(query: String, apiKey: String) {
        viewModelScope.launch {
            try {
                _loadingEverything.value = true
                val apiService = ApiConfig.getApiService()
                val response = apiService.getEverything(query, apiKey)

                if (response.isSuccessful) {
                    val articles = response.body()?.articles
                    _everythingArticles.value = articles
                    _loadingEverything.value = false
                } else {
                    Log.e("MainActivity", "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("MainActivity", "Exception: ${e.message}")
                _loadingEverything.value = false
            }
        }
    }
}