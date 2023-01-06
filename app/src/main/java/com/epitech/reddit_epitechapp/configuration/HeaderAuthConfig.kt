package com.epitech.reddit_epitechapp.configuration

import com.epitech.reddit_epitechapp.TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class HeaderAuthConfig: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "bearer $TOKEN")
            .build()
        return chain.proceed(request)
    }
}
