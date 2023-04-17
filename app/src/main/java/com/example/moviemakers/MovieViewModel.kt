package com.example.moviemakers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SectionViewModel : ViewModel() {
    private val _response = MutableLiveData<MutableList<Movie>>()
    val response : LiveData<MutableList<Movie>>
        get() = _response

    init {
        _response.value = mutableListOf()
        getSections()
    }

    private fun getSections() {
        SectionApi.retrofitService.getSections().enqueue(object: Callback<MutableList<Movie>> {
            override fun onResponse(call: Call<MutableList<Movie>>, response: Response<MutableList<Movie>>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                Log.i("API", "ERROR: " + t.message)
            }

        })
    }
}