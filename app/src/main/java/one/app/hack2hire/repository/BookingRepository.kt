package one.app.hack2hire.repository

import one.app.hack2hire.model.SeatsModelWrapper
import one.app.hack2hire.model.StructuredBooking
import one.app.hack2hire.source.remote.RemoteDataSource
import retrofit2.Call

class BookingRepository(private val remoteDataSource: RemoteDataSource) {
    fun getShows(): Call<StructuredBooking> = remoteDataSource.getShows()
    fun getSeats(showId: String): Call<SeatsModelWrapper> = remoteDataSource.getSeats(showId)
}
