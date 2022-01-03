package com.submission.moviesapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName= "TvShow")
data class TvShows(
        @PrimaryKey(autoGenerate = false)
        @ColumnInfo(name = "id")
        @field:SerializedName("id")
        val id: Int = 0,

        @ColumnInfo(name = "name")
        @field:SerializedName("name")
        var title: String = "",

        @ColumnInfo(name = "poster_path")
        @field:SerializedName("poster_path")
        var poster: String = "",

        @ColumnInfo(name = "backdrop_path")
        @field:SerializedName("backdrop_path")
        var backgroundImages: String = "",

        @ColumnInfo(name = "first_air_date")
        @field:SerializedName("first_air_date")
        val releaseDate: String = "",

        @ColumnInfo(name = "vote_average")
        @field:SerializedName("vote_average")
        var rate: Double = 0.0,

        @ColumnInfo(name = "number_of_seasons")
        @field:SerializedName("number_of_seasons")
        val numberOfSeasons: Int = 0,

        @ColumnInfo(name = "overview")
        @field:SerializedName("overview")
        var description: String = "",

        @ColumnInfo(name = "tagline")
        @field:SerializedName("tagline")
        val tagline: String = "",

        @ColumnInfo(name = "status")
        @field:SerializedName("status")
        val status: String = ""
) : Parcelable

