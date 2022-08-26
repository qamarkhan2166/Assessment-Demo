package com.example.mvvmdemoproject.data.model.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Requirements(

    @SerializedName("minimum") var minimum: String? = null,
    @SerializedName("recommended") var recommended: String? = null

) : Parcelable
