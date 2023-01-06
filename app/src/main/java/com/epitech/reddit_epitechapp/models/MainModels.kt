package com.epitech.reddit_epitechapp.models

import com.epitech.reddit_epitechapp.models.main.MainModelId
import com.epitech.reddit_epitechapp.models.main.SettingModelId
import com.google.gson.annotations.SerializedName

data class MainModels (
    @SerializedName("subreddit")
    var subreddit : MainModelId
)
