package com.cotemustis.rickandmorty.data.api

import okhttp3.Interceptor
import okhttp3.Response

class RMHttpInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //We use this method only for create a chain of interceptors before the build of the call
        //For this app we dont need it, so we create a builder directly
        return chain.proceed(chain.request())
    }
}
