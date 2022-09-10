package one.app.hack2hire.singleton

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import one.app.hack2hire.model.BookingMessage
import one.app.hack2hire.source.remote.ApiSource
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitSetup {
    private const val DEFAULT_ENDPOINT = "https://api.hack2hire.onemount.dev/"
    private val client = OkHttpClient.Builder().addInterceptor(
        HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    ).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(DEFAULT_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val gitHubService = retrofit.create(ApiSource::class.java)

}
