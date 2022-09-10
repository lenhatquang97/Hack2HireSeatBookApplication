package one.app.hack2hire.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class HomeUtils {
    companion object{
        @SuppressLint("SimpleDateFormat")
        fun convertUnixTimeToDateTime(unixTime: Long): String {
            val date = Date(unixTime)
            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
            val timeFormat = SimpleDateFormat("HH:mm")
            return "Start date: " + dateFormat.format(date) + "\n" + "Start time: " + timeFormat.format(date)
        }
    }
}
