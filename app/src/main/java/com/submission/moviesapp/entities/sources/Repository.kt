package com.submission.moviesapp.entities.sources

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.local.LocalDataSource
import com.submission.moviesapp.entities.sources.remote.RemoteDataSource

class Repository private constructor(private val remoteData: RemoteDataSource, private val localDataSource: LocalDataSource) : DataSource {

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(remoteData: RemoteDataSource, localDataSource: LocalDataSource): Repository =
                instance ?: synchronized(this) {
                    instance ?: Repository(remoteData, localDataSource)
                }
    }

    override fun getMoviesTrending(page: Int): LiveData<List<TrendingMovies>> {
        return remoteData.getMoviesTrending(page)
    }

    override fun getTvShowsTrending(page: Int): LiveData<List<TrendingTvShows>> {
        return remoteData.getTvShowsTrending(page)
    }

    override fun getAllMovies(page: Int): LiveData<List<Movies>> {
        return remoteData.getAllMovies(page)
    }

    override fun getMovieById(id: Int): LiveData<Movies> {
        return remoteData.getMovieById(id)
    }

    override fun getAllTvShows(page: Int): LiveData<List<TvShows>> {
        return remoteData.getAllTvShows(page)
    }

    override fun getTvShowById(id: Int): LiveData<TvShows> {
        return remoteData.getTvShowById(id)
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<Movies>> {
        return LivePagedListBuilder(localDataSource.getFavoriteMoviesAsPaged(), 10).build()
    }

    override fun addFavoriteMovies(movie: Movies) {
        localDataSource.addFavoriteMovies(movie)
    }

    override fun removeFavoriteMovie(movie: Movies) {
        localDataSource.removeFavoriteMovie(movie)
    }

    override fun isMovieFavorite(movie: Movies): Boolean {
        return localDataSource.isMovieFavorite(movie)
    }

    override fun getAllFavoriteTvShows(): LiveData<PagedList<TvShows>> {
        return LivePagedListBuilder(localDataSource.getTvShowsFavoriteAsPaged(), 10).build()
    }

    override fun addFavoriteTvShows(tvShows: TvShows) {
        localDataSource.addFavoriteTvShows(tvShows)
    }

    override fun removeFavoriteTvShow(tvShows: TvShows) {
        localDataSource.removeFavoriteTvShows(tvShows)
    }

    override fun isTvShowFavorite(tvShows: TvShows): Boolean {
        return localDataSource.isTvShowFavorite(tvShows)
    }

}