package com.roynaldi19.rm_news.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roynaldi19.rm_news.data.response.ArticlesItem
import com.roynaldi19.rm_news.data.retrofit.ApiConfig

//class MainViewModel: ViewModel() {
//    private val _listTopHeadLine = MutableLiveData<List<ArticlesItem>>()
//    val listTopHeadLine: LiveData<List<ArticlesItem>> = _listTopHeadLine
//
//    private val _isLoading = MutableLiveData<Boolean>()
//    val isLoading: LiveData<Boolean> = _isLoading
//
//    companion object{
//        private const val country = "id"
//        private const val apiKey = "e290ba5e72814fc78a033491a6ab2363"
//
//    }
//
//    init {
//        showTopHeadline()
//    }
//
//    private suspend fun showTopHeadline() {
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getTopHeadLines(country, apiKey)
//        client.e
//    }
//}