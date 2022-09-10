package one.app.hack2hire.model

import com.google.gson.annotations.SerializedName

data class PutModel (

    @SerializedName("meta" ) var meta : MetaPut? = MetaPut()

)

data class MetaPut (

    @SerializedName("code"    ) var code    : Int?    = null,
    @SerializedName("message" ) var message : String? = null

)

data class CanceledBody (

    @SerializedName("status"          ) var status         : String? = null,
    @SerializedName("canceled_date"   ) var canceledDate   : Long?    = null,
    @SerializedName("canceled_reason" ) var canceledReason : String? = null

)
