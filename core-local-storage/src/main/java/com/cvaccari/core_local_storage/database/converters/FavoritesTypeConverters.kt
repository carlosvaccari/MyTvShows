package com.cvaccari.core_local_storage.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.Date

class FavoritesTypeConverters {

    private val gson = Gson()

    fun <T> getTypeToken(): Type = object : TypeToken<List<T>>() {}.type

    @TypeConverter
    fun toGenres(json: String): List<String> {
        return gson.fromJson(json, getTypeToken<List<String>>())
    }

    @TypeConverter
    fun toString(genres: List<String>): String {
        return gson.toJson(genres, getTypeToken<List<String>>())
    }

}
