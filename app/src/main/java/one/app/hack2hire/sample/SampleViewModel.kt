package one.app.hack2hire.sample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import one.app.hack2hire.model.BookingMessage
import one.app.hack2hire.service.Hack2HireRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by lengocanh on 01/08/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
class SampleViewModel : ViewModel() {

    val loadingLiveData = MutableLiveData<Boolean>()
    val reposLiveData = MutableLiveData<BookingMessage>()
    val errorLiveData = MutableLiveData<String>()

    private lateinit var gitHubRepository: Hack2HireRepository

    fun setEndpoint(endpoint: String) {
        gitHubRepository = Hack2HireRepository(endpoint)
    }

    fun getBookingMessage(username: String) {
        loadingLiveData.postValue(true)
        gitHubRepository.listRepo(username).enqueue(object : Callback<BookingMessage> {
            override fun onResponse(call: Call<BookingMessage>, response: Response<BookingMessage>) {
                loadingLiveData.postValue(false)
                if (response.isSuccessful) {
                    response.body()?.let { reposLiveData.postValue(it) }
                } else {
                    errorLiveData.postValue(response.code().toString())
                }
            }

            override fun onFailure(call: Call<BookingMessage>, t: Throwable) {
                loadingLiveData.postValue(false)
                errorLiveData.postValue(t.message ?: "N/A")
            }
        })
    }

}
