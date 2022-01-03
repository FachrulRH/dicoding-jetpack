package com.submission.moviesapp.entities.sources.remote.api

import com.submission.moviesapp.BuildConfig
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @Headers("Authorization: Bearer ${BuildConfig.ApiKey}")
    @GET("trending/movie/week")
    fun getAllMoviesTrending(
        @Query("page") page: Int
    ) : Call<TrendingMoviesResponse>

    @Headers("Authorization: Bearer ${BuildConfig.ApiKey}")
    @GET("trending/tv/week")
    fun getAllTvShowsTrending(
        @Query("page") page: Int
    ) : Call<TrendingTvShowsResponse>

    @Headers("Authorization: Bearer ${BuildConfig.ApiKey}")
    @GET("discover/movie")
    fun getAllMovies(
            @Query("page") page: Int
    ) : Call<MoviesResponse>


    @Headers("Authorization: Bearer ${BuildConfig.ApiKey}")
    @GET("discover/tv")
    fun getAllTvShows(
            @Query("page") page: Int
    ) : Call<TvShowsResponse>

    @Headers("Authorization: Bearer ${BuildConfig.ApiKey}")
    @GET("movie/{id}")
    fun getMoviesDetail(
        @Path("id") moviesId: Int
    ) : Call<Movies>

    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5MTJmMzkwNjczNDA5NzAwYzBjNTY1NjRlZjNhNDZhNiIsInN1YiI6IjVmY2E1NGMyNDM5OTliMDAzYjE3OWQxOCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.TL5LU93kbwZXmqs7DsYVETJ8SbvKmvNYCunepszdJp4")
    @GET("tv/{id}")
    fun getTvShowsDetail(
        @Path("id") tvShowsId: Int
    ) : Call<TvShows>

}