package com.example.mvvmdemoproject.data.model.pagination
import android.os.Parcelable
import com.example.mvvmdemoproject.data.model.games.Platform
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ParentPlatforms(

    @SerializedName("platform") var platform: Platform? = Platform()

) : Parcelable
