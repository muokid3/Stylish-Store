package com.dm.berxley.stylishstore.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class ImageListConverter {

    @TypeConverter
    fun listToString(list: List<String>): String{
        return  list.joinToString(",")
    }

    @TypeConverter
    fun stringToList(string: String): List<String>{
        return string.split(",")
    }
}