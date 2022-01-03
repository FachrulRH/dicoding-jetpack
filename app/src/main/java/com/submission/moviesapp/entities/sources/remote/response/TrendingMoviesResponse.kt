package com.submission.moviesapp.entities.sources.remote.response

import com.google.gson.annotations.SerializedName
import com.submission.moviesapp.entities.TrendingMovies

data class TrendingMoviesResponse(
    @field:SerializedName("results")
	val results: MutableList<TrendingMovies>,
)
