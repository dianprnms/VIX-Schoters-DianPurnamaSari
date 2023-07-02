package com.example.vix_schoters_dianpurnamasari.network

import com.example.vix_schoters_dianpurnamasari.model.Article
import com.example.vix_schoters_dianpurnamasari.model.ResponseDataSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?sources=bbc-news&apiKey=313f9c945f2b4322853651607776a10c")
    fun getAllSources(): Call<ResponseDataSource>

    @GET("articles/{id}")
    fun getArticle(
        @Path("id") id: String,
        @Query("apiKey") apiKey: String = "313f9c945f2b4322853651607776a10c"
    ): Call<Article>

}