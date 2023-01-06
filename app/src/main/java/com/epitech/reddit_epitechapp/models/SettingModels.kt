package com.epitech.reddit_epitechapp.models
import com.epitech.reddit_epitechapp.models.main.SettingModelId
import com.google.gson.annotations.SerializedName

data class SettingModels (
    @SerializedName("subreddit")
    var subreddit : SettingModelId
)