package com.submission.moviesapp.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
@Entity(tableName = "Movie")
class Movies(
        @PrimaryKey(autoGenerate = false)
        @field:SerializedName("id")
        val id: Int = 0,

        @ColumnInfo(name = "overview")
        @field:SerializedName("overview")
        val description: String = "",

        @ColumnInfo(name = "title")
        @field:SerializedName("title")
        val title: String = "",

        @ColumnInfo(name = "poster_path")
        @field:SerializedName("poster_path")
        val poster: String = "",

        @ColumnInfo(name = "backdrop_path")
        @field:SerializedName("backdrop_path")
        val backgroundImages: String = "",


        @ColumnInfo(name = "release_date")
        @field:SerializedName("release_date")
        val releaseDate: String = "",

        @ColumnInfo(name = "vote_average")
        @field:SerializedName("vote_average")
        val rate: Double = 0.0,

        @ColumnInfo(name = "tagline")
        @field:SerializedName("tagline")
        val tagline: String = "",

        @ColumnInfo(name = "status")
        @field:SerializedName("status")
        val status: String = ""
) : Parcelable