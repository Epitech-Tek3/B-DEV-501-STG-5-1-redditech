package com.epitech.reddit_epitechapp.models

import com.epitech.reddit_epitechapp.models.main.myProfileModelId
import com.google.gson.annotations.SerializedName

data class MyProfileModels (
    @SerializedName("subreddit")
    var subreddit : myProfileModelId
)
