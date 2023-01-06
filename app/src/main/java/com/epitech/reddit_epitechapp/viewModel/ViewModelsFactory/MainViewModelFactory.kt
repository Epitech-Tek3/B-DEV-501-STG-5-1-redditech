package com.epitech.reddit_epitechapp.viewModel.ViewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.epitech.reddit_epitechapp.repository.Repository
import com.epitech.reddit_epitechapp.viewModel.ViewModels.MainViewModel
import com.epitech.reddit_epitechapp.viewModel.ViewModels.SettingViewModel

class MainViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}