package one.app.hack2hire.model

import com.google.gson.annotations.SerializedName

data class BookingMessage(@SerializedName("data") var data: Data, @SerializedName("meta") var meta: Meta)

data class Data(@SerializedName("id") var id: String?, @SerializedName("message") var message: String)

data class Meta(@SerializedName("code") var code: Int, @SerializedName("message") var message: String)
