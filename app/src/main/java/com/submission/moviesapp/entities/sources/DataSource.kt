package com.submission.moviesapp.entities.sources

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.entities.TvShows

interface DataSource {

    fun getMoviesTrending(page: Int): LiveData<List<TrendingMovies>>

    fun getTvShowsTrending(page: Int): LiveData<List<TrendingTvShows>>

    fun getAllMovies(page: Int): LiveData<List<Movies>>

    fun getMovieById(id: Int): LiveData<Movies>

    fun getAllTvShows(page: Int): LiveData<List<TvShows>>

    fun getTvShowById(id: Int): LiveData<TvShows>

    fun getAllFavoriteMovies(): LiveData<PagedList<Movies>>

    fun addFavoriteMovies(movie: Movies)

    fun removeFavoriteMovie(movie: Movies)

    fun isMovieFavorite(movie: Movies): Boolean

    fun getAllFavoriteTvShows(): LiveData<PagedList<TvShows>>

    fun addFavoriteTvShows(tvShows: TvShows)

    fun removeFavoriteTvShow(tvShows: TvShows)

    fun isTvShowFavorite(tvShows: TvShows): Boolean
}