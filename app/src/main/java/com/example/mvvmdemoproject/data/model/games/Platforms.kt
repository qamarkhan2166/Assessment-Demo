package com.example.mvvmdemoproject.data.model.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Platforms(

    @SerializedName("platform") var platform: Platform? = Platform(),
    @SerializedName("released_at") var releasedAt: String? = null,
    @SerializedName("requirements") var requirements: Requirements? = Requirements()

) : Parcelable
