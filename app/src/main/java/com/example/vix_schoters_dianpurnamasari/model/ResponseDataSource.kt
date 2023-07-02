package com.example.vix_schoters_dianpurnamasari.model


import com.google.gson.annotations.SerializedName

data class ResponseDataSource(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)