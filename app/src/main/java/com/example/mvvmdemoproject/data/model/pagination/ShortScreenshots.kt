package com.example.mvvmdemoproject.data.model.pagination
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShortScreenshots(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("image") var image: String? = null

) : Parcelable
