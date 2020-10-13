package com.example.media.app.network.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class ResponseImages(
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("hits") val hits: List<ImageInfo> = emptyList()
)

@Parcelize
data class ImageInfo(
    @SerializedName("id") val id: Long = 0,
    @SerializedName("pageURL") val pageUrl: String = "",
    @SerializedName("type") val type: String = "",
    @SerializedName("tags") val tags: String = "",
    @SerializedName("previewURL") val previewURL: String = "",
    @SerializedName("previewWidth") val previewWidth: Int = 0,
    @SerializedName("previewHeight") val previewHeight: Int = 0,
    @SerializedName("webformatURL") val webformatURL: String = "",
    @SerializedName("webformatWidth") val webformatWidth: Int = 0,
    @SerializedName("webformatHeight") val webformatHeight: Int = 0,
    @SerializedName("largeImageURL") val largeImageURL: String = "",
    @SerializedName("imageWidth") val imageWidth: Int = 0,
    @SerializedName("imageHeight") val imageHeight: Int = 0,
    @SerializedName("imageSize") val imageSize: Long = 0,
    @SerializedName("views") val views: Long = 0,
    @SerializedName("downloads") val downloads: Long = 0,
    @SerializedName("favorites") val favorites: Long = 0,
    @SerializedName("likes") val likes: Long = 0,
    @SerializedName("comments") val comments: Long = 0,
    @SerializedName("user_id") val userId: Long = 0,
    @SerializedName("user") val user: String = "",
    @SerializedName("userImageURL") val userImageURL: String = ""
) : Parcelable