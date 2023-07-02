package com.example.vix_schoters_dianpurnamasari.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vix_schoters_dianpurnamasari.model.Article
import com.example.vix_schoters_dianpurnamasari.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _article: MutableLiveData<Article> = MutableLiveData()
    val article: LiveData<Article> = _article

    fun loadArticle(articleId: String) {
        api.getArticle(articleId).enqueue(object : Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                if (response.isSuccessful) {
                    _article.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
