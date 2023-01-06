package com.epitech.reddit_epitechapp.utils

class Constants {

    companion object {
        // RetroFit
        const val BASIC_URL = "https://oauth.reddit.com"


        const val AUTH_URL = "https://www.reddit.com/api/v1/authorize?client_id=%s" +
                "&response_type=code&state=%s&redirect_uri=%s&" +
                "duration=permanent&scope=*"
        const val CLIENT_ID = "NRikgCXXjsdel8Al8o3UdQ"
        const val REDIRECT_URI = "http://localhost"
        const val STATE = "RANDOM_STRING"
        const val ACCESS_TOKEN_URL = "https://www.reddit.com/api/v1/access_token"

        const val ACCESS_TOKEN = "com.epitech.redditech.ACCESS_TOKEN"

        const val VIEW_TYPE_ITEM = 0
        const val VIEW_TYPE_LOADING = 1
    }

}