package com.submission.moviesapp.entities.sources.local

import android.content.Context
import android.provider.Settings
import androidx.paging.DataSource
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.local.dao.MoviesDao
import com.submission.moviesapp.entities.sources.local.dao.TvShowsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LocalDataSource(context: Context) {

    private val moviesDao: MoviesDao
    private val tvShowsDao : TvShowsDao

    companion object {
        fun getInstance(context: Context): LocalDataSource {
            return LocalDataSource(context)
        }
    }

    init {
        val db = AppDatabase.getDatabase(context)
        moviesDao = db.moviesDao()
        tvShowsDao = db.tvShowsDao()
    }

    fun getFavoriteMoviesAsPaged(): DataSource.Factory<Int, Movies> {
        return moviesDao.allMoviesAsPaged()
    }

    fun addFavoriteMovies(movie: Movies) {
        GlobalScope.launch(Dispatchers.Main) { moviesDao.insert(movie) }
    }

    fun removeFavoriteMovie(movie: Movies){
        GlobalScope.launch(Dispatchers.Main) { moviesDao.delete(movie)}
    }

    fun isMovieFavorite(movie: Movies) : Boolean {
        return moviesDao.getById(movie.id) != null
    }

    fun getTvShowsFavoriteAsPaged(): DataSource.Factory<Int, TvShows> {
        return tvShowsDao.allTvShowsAsPaged()
    }

    fun addFavoriteTvShows(tvShow: TvShows) {
        GlobalScope.launch(Dispatchers.Main) { tvShowsDao.insert(tvShow) }
    }

    fun removeFavoriteTvShows(tvShow: TvShows) {
        GlobalScope.launch(Dispatchers.Main) { tvShowsDao.delete(tvShow) }
    }

    fun isTvShowFavorite(tvShow: TvShows) : Boolean {
        return tvShowsDao.getById(tvShow.id) != null
    }
}