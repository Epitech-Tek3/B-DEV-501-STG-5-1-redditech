package com.epitech.reddit_epitechapp.viewModel.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitech.reddit_epitechapp.models.MainModels
import com.epitech.reddit_epitechapp.models.SettingModels
import com.epitech.reddit_epitechapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {
    val rep: MutableLiveData<Response<MainModels>> = MutableLiveData()

    fun getMainData() {
        viewModelScope.launch {
            val response = repository.getMainData()
            rep.value = response
        }
    }

}