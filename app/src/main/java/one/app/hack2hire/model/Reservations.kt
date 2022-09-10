import com.google.gson.annotations.SerializedName

data class ReservationWrapper(
    @SerializedName("data" ) var data : Data? = Data()
)

data class User (

    @SerializedName("name"        ) var name        : String? = null,
    @SerializedName("phone_number") var phoneNumber : String? = null

)

data class Reservations (

    @SerializedName("reservation_id" ) var reservationId : String? = null,
    @SerializedName("code"           ) var code          : String? = null,
    @SerializedName("show_id"        ) var showId        : String? = null,
    @SerializedName("seat_code"      ) var seatCode      : String? = null,
    @SerializedName("status"         ) var status        : String? = null,
    @SerializedName("booked_date"    ) var bookedDate    : Long?    = null,
    @SerializedName("user"           ) var user          : User?   = User()

)

data class Data (

    @SerializedName("reservations" ) var reservations : ArrayList<Reservations> = arrayListOf(),
    @SerializedName("total"        ) var total        : Long?                    = null

)

//Post
data class PostReservations (

    @SerializedName("user"       ) var user      : User?             = User(),
    @SerializedName("seat_codes" ) var seatCodes : MutableList<String> = mutableListOf()

)
