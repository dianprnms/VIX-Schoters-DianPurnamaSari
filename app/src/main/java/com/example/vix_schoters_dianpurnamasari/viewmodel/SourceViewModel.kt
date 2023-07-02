package com.example.vix_schoters_dianpurnamasari.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vix_schoters_dianpurnamasari.model.Article
import com.example.vix_schoters_dianpurnamasari.model.ResponseDataSource
import com.example.vix_schoters_dianpurnamasari.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SourceViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _dataSource: MutableLiveData<List<Article>?> = MutableLiveData()
    val dataSource: LiveData<List<Article>?> = _dataSource

    fun callApiSource() {
        api.getAllSources().enqueue(object : Callback<ResponseDataSource> {
            override fun onResponse(call: Call<ResponseDataSource>, response: Response<ResponseDataSource>) {
                if (response.isSuccessful) {
                    val data = response.body()?.articles
                    _dataSource.postValue(data)
                    Log.d(TAG, "onResponse: $data")
                } else {
                    Log.d(TAG, "onResponse: Unsuccessfully")
                }
            }

            override fun onFailure(call: Call<ResponseDataSource>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "SourceViewModel"
    }
}
