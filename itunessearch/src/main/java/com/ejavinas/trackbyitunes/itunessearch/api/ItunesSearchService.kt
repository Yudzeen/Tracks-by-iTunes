package com.ejavinas.trackbyitunes.itunessearch.api

import android.util.Log
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Service for connecting to ITunes Search API
 */
interface ItunesSearchService {

    @GET("search?term=star&country=au&media=all")
    fun fetchItems(): Single<FetchItemsResponse>

    companion object {
        private const val TAG = "ItunesSearchService"
        private const val BASE_URL = "https://itunes.apple.com/"

        fun create(): ItunesSearchService {
            val logger = object : HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.d(TAG, message)
                }
            }

            val interceptor = HttpLoggingInterceptor(logger)
                .apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ItunesSearchService::class.java)
        }
    }
}