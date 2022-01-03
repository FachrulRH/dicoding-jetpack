package com.submission.moviesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.Repository

class DetailTvShowsViewModel(private val repository: Repository) : ViewModel() {

    fun generateTvShows(id: Int) : LiveData<TvShows> = repository.getTvShowById(id)

    fun addFavorite(tvShows: TvShows) {
        repository.addFavoriteTvShows(tvShows)
    }

    fun removeFavorite(tvShows: TvShows) {
        repository.removeFavoriteTvShow(tvShows)
    }

    fun isFavorite(tvShows: TvShows) : Boolean {
        return repository.isTvShowFavorite(tvShows)
    }

}