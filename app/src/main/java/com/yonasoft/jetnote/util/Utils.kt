package com.yonasoft.jetnote.util

import java.text.SimpleDateFormat
import java.util.*

class Utils {
    fun formatDate(time:Long):String{
        val date = Date(time)
        val format =  SimpleDateFormat("EEE, MMM dd yyyy hh:mm aaa", Locale.getDefault())
        return format.format(date)
    }
}