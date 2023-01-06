package com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epitech.reddit_epitechapp.repository.Repository
import com.epitech.reddit_epitechapp.viewModel.ViewModels.SettingViewModel

class SettingViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(repository) as T
    }
    //var collectionType: Type = object : TypeToken<Collection<ChannelSearchEnum?>?>() {}.type
    //var enums: Collection<ChannelSearchEnum> = gson.fromJson(yourJson, collectionType)
}