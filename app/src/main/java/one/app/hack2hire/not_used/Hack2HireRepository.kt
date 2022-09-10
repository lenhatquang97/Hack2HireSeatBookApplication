package one.app.hack2hire.not_used

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import one.app.hack2hire.model.BookingMessage
import one.app.hack2hire.source.remote.ApiSource
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by lengocanh on 01/08/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class Hack2HireRepository(endpoint: String) {

    private val client = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    ).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(endpoint)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val gitHubService = retrofit.create(ApiSource::class.java)

    fun listRepo(user: String): Call<BookingMessage> {
        return gitHubService.getBookingMessage(user)
    }
}
