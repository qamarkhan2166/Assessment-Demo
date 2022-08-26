package com.example.mvvmdemoproject.data.model.pagination
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Years(

    @SerializedName("from") var from: Int? = null,
    @SerializedName("to") var to: Int? = null,
    @SerializedName("filter") var filter: String? = null,
    @SerializedName("decade") var decade: Int? = null,
    @SerializedName("years") var years: ArrayList<Years> = arrayListOf(),
    @SerializedName("nofollow") var nofollow: Boolean? = null,
    @SerializedName("count") var count: Int? = null

) : Parcelable
