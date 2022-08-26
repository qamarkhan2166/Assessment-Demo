package com.example.mvvmdemoproject.data.model.pagination
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Filters(

    @SerializedName("years") var years: ArrayList<Years> = arrayListOf()

) : Parcelable
