package com.submission.moviesapp.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.Repository
import kotlinx.coroutines.Dispatchers

class TvShowsViewModel(private val repository: Repository) : ViewModel() {

    fun generateTvShows(page: Int): LiveData<List<TvShows>> = repository.getAllTvShows(page)

}