package com.submission.moviesapp.entities.sources.remote.response

import com.google.gson.annotations.SerializedName
import com.submission.moviesapp.entities.TrendingTvShows

data class TrendingTvShowsResponse(
    @field:SerializedName("results")
	val results: MutableList<TrendingTvShows>,
)
