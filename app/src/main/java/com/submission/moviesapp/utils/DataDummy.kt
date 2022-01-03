package com.submission.moviesapp.utils

import android.util.Log
import com.google.gson.Gson
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.remote.response.MoviesResponse
import com.submission.moviesapp.entities.sources.remote.response.TrendingMoviesResponse
import com.submission.moviesapp.entities.sources.remote.response.TrendingTvShowsResponse
import com.submission.moviesapp.entities.sources.remote.response.TvShowsResponse
import java.io.IOException
import java.io.InputStream

object DataDummy {

    private val gson = Gson()

    fun generateTrendingMovies() : List<TrendingMovies> {
        return gson.fromJson(parsingJsonFile("trending_movies.json"), TrendingMoviesResponse::class.java).results
    }

    fun generateTrendingTvShows() : List<TrendingTvShows>{
        return gson.fromJson(parsingJsonFile("trending_tv.json"), TrendingTvShowsResponse::class.java).results
    }

    fun generateAllMovies() : List<Movies> {
        return gson.fromJson(parsingJsonFile("all_movies.json"), MoviesResponse::class.java).results
    }

    fun generateAlltvShows() : List<TvShows> {
        return gson.fromJson(parsingJsonFile("all_tv.json"), TvShowsResponse::class.java).results
    }

    fun generateDetailMovie() : Movies {
        return gson.fromJson(parsingJsonFile("movies.json"), Movies::class.java)
    }

    fun generateDetailtvShow() : TvShows {
        return gson.fromJson(parsingJsonFile("tv_shows.json"), TvShows::class.java)
    }

    private fun parsingJsonFile(fileName: String): String? {
        var json: String? = null
        try {
            val input: InputStream? = this.javaClass.classLoader?.getResourceAsStream(fileName)
            val size = input?.available()
            val buffer = size?.let { ByteArray(it) }
            input?.read(buffer)
            input?.close()
            json = buffer?.let { String(it, charset("UTF-8")) }

        } catch (ex: IOException) {
            Log.e("Dummy", ex.localizedMessage as String)
        }

        return json
    }
}