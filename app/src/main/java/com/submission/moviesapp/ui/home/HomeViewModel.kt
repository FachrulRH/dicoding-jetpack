package com.submission.moviesapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.entities.sources.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun generateMoviesTrending(page: Int) : LiveData<List<TrendingMovies>> = repository.getMoviesTrending(page)

    fun generateTvShowsTrending(page: Int) : LiveData<List<TrendingTvShows>> = repository.getTvShowsTrending(page)

}