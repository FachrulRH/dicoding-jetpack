package com.submission.moviesapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class TrendingMovies (

        @field:SerializedName("backdrop_path")
        val backgroundImages: String = "",

        @field:SerializedName("vote_average")
        val rate: Double = 0.0,

        @field:SerializedName("title")
        val title: String = "",
)