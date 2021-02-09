package com.simple.pos.shared.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class AcceptJsonInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .build()
        return chain.proceed(request)
    }
}