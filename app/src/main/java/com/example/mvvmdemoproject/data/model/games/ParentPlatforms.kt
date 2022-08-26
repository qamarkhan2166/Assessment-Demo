package com.example.mvvmdemoproject.data.model.games

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParentPlatforms(

    @SerializedName("platform") var platform: Platform? = Platform()

) : Parcelable
