package com.example.mvvmdemoproject.data.model.pagination
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Stores(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("store") var store: Store? = Store()

) : Parcelable
