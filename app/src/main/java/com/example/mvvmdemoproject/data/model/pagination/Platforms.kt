package com.example.mvvmdemoproject.data.model.pagination
import android.os.Parcelable
import com.example.mvvmdemoproject.data.model.games.Platform
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Platforms(

    @SerializedName("platform") var platform: Platform? = Platform(),
    @SerializedName("released_at") var releasedAt: String? = null,
    @SerializedName("requirements_en") var requirementsEn: String? = null,
    @SerializedName("requirements_ru") var requirementsRu: String? = null

) : Parcelable
