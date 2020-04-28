package by.dzmitrey.danilau.muzhelpermessenger.network

import by.dzmitrey.danilau.muzhelpermessenger.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber

class RequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().url(BASE_URL).build()
        Timber.d(request.toString())
        return chain.proceed(request)
    }
}