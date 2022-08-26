package com.example.mvvmdemoproject.data.model.games
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddedByStatus(

    @SerializedName("yet") var yet: Int? = null,
    @SerializedName("owned") var owned: Int? = null,
    @SerializedName("beaten") var beaten: Int? = null,
    @SerializedName("toplay") var toplay: Int? = null,
    @SerializedName("dropped") var dropped: Int? = null,
    @SerializedName("playing") var playing: Int? = null

) : Parcelable
