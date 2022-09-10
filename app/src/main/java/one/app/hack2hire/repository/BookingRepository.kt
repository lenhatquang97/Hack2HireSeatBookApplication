package one.app.hack2hire.repository

import ReservationWrapper
import one.app.hack2hire.model.PutModel
import one.app.hack2hire.model.SeatsModelWrapper
import one.app.hack2hire.model.StructuredBooking
import one.app.hack2hire.source.remote.RemoteDataSource
import retrofit2.Call

class BookingRepository(private val remoteDataSource: RemoteDataSource) {
    fun getShows(): Call<StructuredBooking> = remoteDataSource.getShows()
    fun getSeats(showId: String): Call<SeatsModelWrapper> = remoteDataSource.getSeats(showId)
    fun postReservations(showId: String): Call<ReservationWrapper> = remoteDataSource.postReservations(showId)
    fun putReservation(showId: String, reservationId: String): Call<PutModel> = remoteDataSource.putReservation(showId, reservationId)
}
