package com.example.mvvmdemoproject.data.model.games
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tags(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("slug") var slug: String? = null,
    @SerializedName("language") var language: String? = null,
    @SerializedName("games_count") var gamesCount: Int? = null,
    @SerializedName("image_background") var imageBackground: String? = null

) : Parcelable
