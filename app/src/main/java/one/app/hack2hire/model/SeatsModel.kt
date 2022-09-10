package one.app.hack2hire.model

import com.google.gson.annotations.SerializedName
data class SeatsModelWrapper(
    @SerializedName("data")
    val data: SeatsData
)
data class SeatsData(
    @SerializedName("seat_list")
    val seats: List<SeatsModel>
)

data class SeatsModel(
    @SerializedName("seat_id")
    var seatId: String,
    @SerializedName("seat_code")
    var seatCode: String,
    @SerializedName("show_id")
    var showId: String,
    @SerializedName("status")
    var status: String,
    @SerializedName("booked_date")
    var bookedDate: Long,
)
