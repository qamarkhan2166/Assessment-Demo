package com.example.mvvmdemoproject.data.model.games
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ratings(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("count") var count: Int? = null,
    @SerializedName("percent") var percent: Double? = null

) : Parcelable
