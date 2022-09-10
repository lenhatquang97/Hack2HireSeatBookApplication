package one.app.hack2hire.source.remote

import one.app.hack2hire.model.BookingMessage
import one.app.hack2hire.model.ShowsModel
import one.app.hack2hire.model.StructuredBooking
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


/**
 * Created by lengocanh on 29/07/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
interface ApiSource {
    @GET("booking/messages/{message_id}")
    fun getBookingMessage(@Path("message_id") messageId: String): Call<BookingMessage>

    @GET("booking/shows")
    fun getShows(): Call<StructuredBooking>

    @GET("booking/shows/{show_id}/seats")
    fun getSeats(@Path("show_id") showId: String): Call<Int>

    @POST("booking/shows/{show_id}/reservations")
    fun postReservation(@Path("show_id") showId: String): Call<Int>

    //Check booking
    @GET("booking/shows/{show_id}/reservations")
    fun checkBooking(@Path("show_id") showId: String): Call<Int>

    @PUT("booking/shows/{show_id}/reservations/{reservation_code}")
    fun putReservation(@Path("show_id") showId: String, @Path("reservation_code") reservationCode: String): Call<Int>

}
