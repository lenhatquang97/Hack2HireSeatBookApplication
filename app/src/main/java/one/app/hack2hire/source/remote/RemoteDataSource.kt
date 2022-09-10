package one.app.hack2hire.source.remote

import PostReservations
import ReservationWrapper
import User
import one.app.hack2hire.globals.StaticBooking
import one.app.hack2hire.model.SeatsModelWrapper
import one.app.hack2hire.model.ShowsModel
import one.app.hack2hire.model.StructuredBooking
import one.app.hack2hire.singleton.RetrofitSetup
import retrofit2.Call

class RemoteDataSource {
    fun getShows(): Call<StructuredBooking>{
        return RetrofitSetup.gitHubService.getShows()
    }
    fun getSeats(showId: String): Call<SeatsModelWrapper>{
        return RetrofitSetup.gitHubService.getSeats(showId)
    }
    fun postReservations(showId: String): Call<ReservationWrapper>{
        val req: PostReservations = PostReservations(User(name="Le Nhat Quang", phoneNumber = "0123456789"), StaticBooking.bookingSeats.map { it.seatCode }.toMutableList())
        return RetrofitSetup.gitHubService.postReservation (showId, req)
    }
}
