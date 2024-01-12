package com.roynaldi19.rm_news.data.response

import com.google.gson.annotations.SerializedName

data class EverythingResponse(

	@field:SerializedName("totalResults")
	val totalResults: Int,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem>,

	@field:SerializedName("status")
	val status: String
)