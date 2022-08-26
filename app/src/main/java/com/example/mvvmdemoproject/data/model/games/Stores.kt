package com.example.mvvmdemoproject.data.model.games
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stores(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("store") var store: Store? = Store()

) : Parcelable
