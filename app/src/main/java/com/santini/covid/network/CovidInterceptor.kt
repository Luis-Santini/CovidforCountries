package com.santini.covid.network

import okhttp3.Interceptor
import okhttp3.Response

class CovidInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authenticatorRequest = originalRequest.newBuilder().build()
        return chain.proceed(authenticatorRequest)
    }
}