package com.submission.moviesapp.entities.sources.remote.response

import com.google.gson.annotations.SerializedName
import com.submission.moviesapp.entities.Movies

data class MoviesResponse(
	@field:SerializedName("results")
	val results: MutableList<Movies>,
)