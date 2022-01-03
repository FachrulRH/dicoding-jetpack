package com.submission.moviesapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.sources.Repository

class DetailMoviesViewModel(private val repository: Repository) : ViewModel() {

    fun generateMovies(id: Int) : LiveData<Movies> = repository.getMovieById(id)

    fun addFavorite(movie: Movies) {
        repository.addFavoriteMovies(movie)
    }

    fun removeFavorite(movie: Movies) {
        repository.removeFavoriteMovie(movie)
    }

    fun isFavorite(movie: Movies) : Boolean {
        return repository.isMovieFavorite(movie)
    }
}