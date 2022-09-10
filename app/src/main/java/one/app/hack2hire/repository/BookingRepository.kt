package one.app.hack2hire.repository

import one.app.hack2hire.model.StructuredBooking
import one.app.hack2hire.source.remote.RemoteDataSource
import retrofit2.Call

class BookingRepository(private val remoteDataSource: RemoteDataSource) {
    fun getShows(): Call<StructuredBooking> = remoteDataSource.getShows()
}
