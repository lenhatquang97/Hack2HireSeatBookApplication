package one.app.hack2hire.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.app.hack2hire.Hack2HireApplication
import one.app.hack2hire.model.SeatsModel
import one.app.hack2hire.model.SeatsModelWrapper
import one.app.hack2hire.model.ShowsModel
import one.app.hack2hire.model.StructuredBooking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookingViewModel: ViewModel() {
    private var _showLists = MutableLiveData<List<ShowsModel>>().apply { value = emptyList() }
    val showLists: MutableLiveData<List<ShowsModel>> = _showLists

    private var _seatLists = MutableLiveData<List<SeatsModel>>().apply { value = emptyList() }
    val seatLists: MutableLiveData<List<SeatsModel>>  =_seatLists

    fun listAllSeats(showId: String){
        viewModelScope.launch(Dispatchers.IO) {
            Hack2HireApplication.bookingRepository.getSeats(showId).enqueue(object : Callback<SeatsModelWrapper> {
                override fun onResponse(call: Call<SeatsModelWrapper>, response: Response<SeatsModelWrapper>) {
                    if (response.code() == 200) {
                        val data = response.body()?.data?.seats
                        _seatLists.postValue(data)
                    }
                }

                override fun onFailure(call: Call<SeatsModelWrapper>, t: Throwable) {
                    Log.e("BookingViewModel", "Error in fetching shows", t)
                }
            })
        }
    }

    fun listAllShows(){
        viewModelScope.launch(Dispatchers.IO){
            Hack2HireApplication.bookingRepository.getShows().enqueue(object : Callback<StructuredBooking> {
                override fun onFailure(call: Call<StructuredBooking>, t: Throwable) {
                    Log.e("BookingViewModel", "Error while fetching shows", t)
                }

                override fun onResponse(call: Call<StructuredBooking>, response: Response<StructuredBooking>) {
                    Log.d("BookingViewModel", "Shows fetched successfully")
                    if(response.code() == 200){
                        val data = response.body()?.data?.shows?.filter { it.name != "string"}?.filter { it.name != "test" }?.filter { it.name != "999" }
                        _showLists.postValue(data)
                    }
                }
            })
        }

    }
}
