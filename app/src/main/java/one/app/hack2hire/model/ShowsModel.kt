package one.app.hack2hire.model

import com.google.gson.annotations.SerializedName

data class StructuredBooking(
    @SerializedName("data")
    var data: BookingData,
    @SerializedName("meta")
    var meta: MetaData
)
data class BookingData(
    @SerializedName("total")
    var total: Int,
    @SerializedName("shows")
    var shows: List<ShowsModel>
)

data class MetaData(
    @SerializedName("code")
    var code: Int,
    @SerializedName("message")
    var message: String
)

data class ShowsModel(
    @SerializedName("show_id")
    var showId: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("start_date")
    var startDate: Long,
    @SerializedName("image_url")
    var imageUrl: String,
)
