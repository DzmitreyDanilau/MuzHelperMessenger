package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import by.dzmitrey.danilau.muzhelpermessenger.network.ApiService
import by.dzmitrey.danilau.muzhelpermessenger.network.RequestInterceptor
import by.dzmitrey.danilau.muzhelpermessenger.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp() = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient.Builder): Retrofit {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor(RequestInterceptor())

        val retroBuilder: Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
        retroBuilder.client(httpClient.build())

        return retroBuilder.build()
    }

    @Provides
    @Singleton
    fun apiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}