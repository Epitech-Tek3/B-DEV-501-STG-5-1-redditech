package com.epitech.reddit_epitechapp.viewModel.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitech.reddit_epitechapp.models.PopularModels
import com.epitech.reddit_epitechapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class PopularViewModel(private val repository: Repository): ViewModel() {
    val rep: MutableLiveData<Response<PopularModels>> = MutableLiveData()

    fun getPopularData(limit: String, after: String) {
        viewModelScope.launch {
            val response = repository.getPopularData(limit, after)
            rep.value = response
        }
    }
    fun getDefaultData(limit: String, after: String) {
        viewModelScope.launch {
            val response = repository.getDefaultData(limit, after)
            rep.value = response
        }
    }

    fun getNewData(limit: String, after: String) {
        viewModelScope.launch {
            val response = repository.getNewData(limit, after)
            rep.value = response
        }
    }
}