package com.epitech.reddit_epitechapp.viewModel.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitech.reddit_epitechapp.models.SettingModels
import com.epitech.reddit_epitechapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SettingViewModel(private val repository: Repository): ViewModel() {
    val rep: MutableLiveData<Response<SettingModels>> = MutableLiveData()

    fun getSettingData() {
        viewModelScope.launch {
            val response = repository.getSettingData()
            rep.value = response
        }
    }

}