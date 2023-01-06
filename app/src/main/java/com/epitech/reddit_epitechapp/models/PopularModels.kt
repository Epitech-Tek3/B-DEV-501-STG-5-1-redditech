package com.epitech.reddit_epitechapp.models

import com.google.gson.annotations.SerializedName

data class DataPopularModelId (
    var title: String,
    var header_img: String,
    var display_name: String,
    var subscribers: Int,
    val icon_img: String,
    var display_name_prefixed: String,
    var banner_img: String,
    var public_description: String,
    var banner_size: List<Int>

)

data class ChildrenPopularModelId (
    var kind: String,
    var data: DataPopularModelId
)

data class PopularModelId (
    var after: String,
    @SerializedName("children")
    var children: List<ChildrenPopularModelId>
)

data class PopularModels (
    @SerializedName("data")
    var children : PopularModelId
)