package com.submission.moviesapp.entities.sources.remote.response

import com.google.gson.annotations.SerializedName
import com.submission.moviesapp.entities.TvShows

data class TvShowsResponse(

	@field:SerializedName("results")
	val results: MutableList<TvShows>,

)

