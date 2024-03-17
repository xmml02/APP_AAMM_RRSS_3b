package com.app.olders_rrss.DB

import androidx.room.TypeConverter
import com.app.olders_rrss.Clases.screenActiv
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromString(value: String): List<screenActiv> {
        val listType = object : TypeToken<List<screenActiv>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<screenActiv>): String {
        return Gson().toJson(value)
    }

}


