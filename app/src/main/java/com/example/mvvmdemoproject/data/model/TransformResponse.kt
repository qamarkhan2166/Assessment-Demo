package com.example.mvvmdemoproject.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class TransformResponse(
    val added: Int,
    val background_image: String,
    val description: String,
    val description_raw: String,
    val dominant_color: String,
    val id: Int,
    val metacritic: Int,
    val name: String,
    val playtime: Int,
    val rating: Double,
    val rating_top: Int,
    val ratings_count: Int,
    val reddit_count: Int,
    val released: String,
    val reviews_count: Int,
    val reviews_text_count: Int,
    val saturated_color: String,
    val slug: String,
    val suggestions_count: Int,
    val tba: Boolean,
    val updated: String
) : Parcelable {
    val getCreatedDate = getChatMessagesDate((released), "yyyy-MM-dd")
}

fun getChatMessagesDate(stringDate: String, format: String): String {
    val date = getDateFromString(stringDate, format)
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    return sdf.format(date ?: "")
}

fun getDateFromString(date: String?, format: String): Date? {
    return SimpleDateFormat(format, Locale.US).parse(date!!)
}
