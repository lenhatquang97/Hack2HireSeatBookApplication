package one.app.hack2hire.service

import one.app.hack2hire.model.BookingMessage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by lengocanh on 29/07/2022.
 * Copyright (c) {2022} VinID. All rights reserved.
 */
interface Hack2HireService {
    @GET("booking/messages/{message_id}")
    fun getBookingMessage(@Path("message_id") messageId: String): Call<BookingMessage>
}
