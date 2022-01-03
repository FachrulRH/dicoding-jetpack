package com.submission.moviesapp.ui.favorite

import androidx.lifecycle.ViewModel
import com.submission.moviesapp.entities.sources.Repository

class FavoriteViewModel(private val repository: Repository) : ViewModel() {

    fun getMoviesFavorite() = repository.getAllFavoriteMovies()

    fun getTvShowsFavorite() = repository.getAllFavoriteTvShows()

}