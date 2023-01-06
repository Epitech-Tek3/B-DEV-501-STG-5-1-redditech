package com.epitech.reddit_epitechapp.viewModel.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epitech.reddit_epitechapp.models.MyProfileModels
import com.epitech.reddit_epitechapp.models.SettingModels
import com.epitech.reddit_epitechapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MyProfileViewModel(private val repository: Repository): ViewModel() {
    val rep: MutableLiveData<Response<MyProfileModels>> = MutableLiveData()

    fun getMyProfileData() {
        viewModelScope.launch {
            val response = repository.getMyProfileData()
            rep.value = response
        }
    }

}