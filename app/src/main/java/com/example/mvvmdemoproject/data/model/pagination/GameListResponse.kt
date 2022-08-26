package com.example.mvvmdemoproject.data.model.pagination

import android.os.Parcelable
import com.example.mvvmdemoproject.data.model.TransformResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameListResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf(),
    @SerializedName("seo_title") var seoTitle: String? = null,
    @SerializedName("seo_description") var seoDescription: String? = null,
    @SerializedName("seo_keywords") var seoKeywords: String? = null,
    @SerializedName("seo_h1") var seoH1: String? = null,
    @SerializedName("noindex") var noindex: Boolean? = null,
    @SerializedName("nofollow") var nofollow: Boolean? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("filters") var filters: Filters? = Filters(),
    @SerializedName("nofollow_collections") var nofollowCollections: ArrayList<String> = arrayListOf()

) : Parcelable

fun GameListResponse.toViewData() = results.map {
    TransformResponse(
        added = it.added ?: 0,
        background_image = it.backgroundImage ?: "",
        description = /*description ?: */it.platforms.first().platform?.name ?: "",
        description_raw = description ?: "",
        dominant_color = it.dominantColor ?: "",
        id = it.id ?: 0,
        metacritic = it.metacritic ?: 0,
        name = it.name ?: "",
        playtime = it.playtime ?: 0,
        rating = it.rating ?: 0.0,
        rating_top = it.ratingTop ?: 0,
        ratings_count = it.ratingsCount ?: 0,
        reddit_count = it.ratingsCount ?: 0,
        released = it.released ?: "",
        reviews_count = it.reviewsCount ?: 0,
        reviews_text_count = it.reviewsTextCount ?: 0,
        saturated_color = it.saturatedColor ?: "",
        slug = it.slug ?: "",
        suggestions_count = it.suggestionsCount ?: 0,
        tba = it.tba ?: false,
        updated = it.updated ?: ""
    )
}
