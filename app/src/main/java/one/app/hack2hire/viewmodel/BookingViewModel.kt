package one.app.hack2hire.viewmodel

import ReservationWrapper
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import one.app.hack2hire.Hack2HireApplication
import one.app.hack2hire.R
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

    fun postReservations(showId: String, context: Context, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            Hack2HireApplication.bookingRepository.postReservations(showId).enqueue(object : Callback<ReservationWrapper> {
                override fun onResponse(call: Call<ReservationWrapper>, response: Response<ReservationWrapper>) {
                    if (response.code() == 200) {
                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                        onSuccess()
                    } else {
                        println("Response code: ${response.toString()}")
                        Toast.makeText(context, "Oops!!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<ReservationWrapper>, t: Throwable) {
                    Log.d("BookingViewModel", "postReservations: ${t.message}")
                    Toast.makeText(context, "Oh no!!", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

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
