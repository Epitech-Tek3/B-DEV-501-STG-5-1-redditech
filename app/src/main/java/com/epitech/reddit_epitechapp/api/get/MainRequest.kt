package com.epitech.reddit_epitechapp.api.get

import com.epitech.reddit_epitechapp.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MainRequest {
    @GET("/api/v1/me")
    suspend fun getMainData(): Response<MainModels>

}

interface SettingRequest {
    @GET("/api/v1/me")
    suspend fun getSettingData(): Response<SettingModels>
}

interface MyProfileRequest {
    @GET("/api/v1/me")
    suspend fun getMyProfileData(): Response<MyProfileModels>
}

interface PopularRequest {

    @Headers("Content-Type: application/json")
    @GET("/subreddits/popular")
    suspend fun getPopularData(@Query("limit") limit: String,
                               @Query("after") after: String): Response<PopularModels>

    @Headers("Content-Type: application/json")
    @GET("/subreddits/default")
    suspend fun getDefaultData(@Query("limit") limit: String,
                               @Query("after") after: String): Response<PopularModels>

    @Headers("Content-Type: application/json")
    @GET("/subreddits/new")
    suspend fun getNewData(@Query("limit") limit: String,
                               @Query("after") after: String): Response<PopularModels>

}