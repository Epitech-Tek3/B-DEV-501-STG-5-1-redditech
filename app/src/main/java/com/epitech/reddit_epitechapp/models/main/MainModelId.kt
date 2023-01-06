package com.epitech.reddit_epitechapp.models.main

data class MainModelId (
    var display_name: String,
    var icon_img: String,
    var public_description:String
)

data class SettingModelId (
    var banner_img: String,
    var title: String,
    var icon_img:String,
    var public_description: String,
    var over_18: Boolean,
    var accept_followers: Boolean
)

data class myProfileModelId (
    var display_name_prefixed: String,
    var public_description: String,
    var icon_img: String,
    var banner_img: String
)