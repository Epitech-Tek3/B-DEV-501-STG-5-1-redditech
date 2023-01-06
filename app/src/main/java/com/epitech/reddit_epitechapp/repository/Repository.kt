package com.epitech.reddit_epitechapp.repository

import android.util.Log
import com.epitech.reddit_epitechapp.api.*
import com.epitech.reddit_epitechapp.models.*
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getMainData() : Response<MainModels> {
        return RetrofitInstance.api.getMainData()
    }

    suspend fun getSettingData() : Response<SettingModels> {
        return RetrofitSettingInstance.api.getSettingData()
    }

    suspend fun getMyProfileData() : Response<MyProfileModels> {
        return RetrofitMyProfileInstance.api.getMyProfileData()
    }

    suspend fun getPopularData(limit: String, after: String) : Response<PopularModels> {
        return RetrofitPopularInstance.api.getPopularData(limit, after)
    }

    suspend fun getDefaultData(limit: String, after: String) : Response<PopularModels> {
        return RetrofitPopularInstance.api.getDefaultData(limit, after)
    }

    suspend fun getNewData(limit: String, after: String) : Response<PopularModels> {
        return RetrofitPopularInstance.api.getNewData(limit, after)
    }

}
