package com.submission.moviesapp.ui.movies

import com.submission.moviesapp.entities.Movies

interface OnMoviesClickCallBack {
    fun onItemClicked(data: Movies)
}