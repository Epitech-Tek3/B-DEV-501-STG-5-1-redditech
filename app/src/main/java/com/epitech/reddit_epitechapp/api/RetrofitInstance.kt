package com.epitech.reddit_epitechapp.api

import android.util.Log
import com.epitech.reddit_epitechapp.api.get.*
import com.epitech.reddit_epitechapp.configuration.HeaderAuthConfig
import com.epitech.reddit_epitechapp.utils.Constants.Companion.BASIC_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.google.gson.GsonBuilder

object RetrofitInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(HeaderAuthConfig())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : MainRequest by lazy {
        retrofit.create(MainRequest::class.java)
    }
}

object RetrofitSettingInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(HeaderAuthConfig())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : SettingRequest by lazy {
        retrofit.create(SettingRequest::class.java)
    }
}

object RetrofitMyProfileInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(HeaderAuthConfig())
    }.build()

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : MyProfileRequest by lazy {
        retrofit.create(MyProfileRequest::class.java)
    }
}


object RetrofitPopularInstance {
    private val client = OkHttpClient.Builder().apply {
        addInterceptor(HeaderAuthConfig())
    }.build()

    var gson = GsonBuilder()
        .setLenient()
        .create()
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASIC_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val api : PopularRequest by lazy {
        retrofit.create(PopularRequest::class.java)
    }
}