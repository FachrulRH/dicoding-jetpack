package com.submission.moviesapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.sources.Repository
import kotlinx.coroutines.Dispatchers

class MoviesViewModel(private val repository: Repository) : ViewModel() {

    fun generateMovies(page: Int) : LiveData<List<Movies>> = repository.getAllMovies(page)

}