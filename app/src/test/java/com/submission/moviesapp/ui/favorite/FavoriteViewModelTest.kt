package com.submission.moviesapp.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.Repository
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovies: Observer<PagedList<Movies>>

    @Mock
    private lateinit var observerTvShows: Observer<PagedList<TvShows>>

    @Mock
    private lateinit var pagedListMovies: PagedList<Movies>

    @Mock
    private lateinit var pagedListTvShows: PagedList<TvShows>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(repository)
    }

    @Test
    fun getMoviesFavorite() {
        val dummyMovies = pagedListMovies
        val moviesFav = MutableLiveData<PagedList<Movies>>()
        moviesFav.value = dummyMovies

        `when`(repository.getAllFavoriteMovies()).thenReturn(moviesFav)
        viewModel.getMoviesFavorite().observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyMovies)
    }

    @Test
    fun getTvShowsFavorite() {
        val dummyTvShows = pagedListTvShows
        val tvShowsFav = MutableLiveData<PagedList<TvShows>>()
        tvShowsFav.value = dummyTvShows

        `when`(repository.getAllFavoriteTvShows()).thenReturn(tvShowsFav)
        viewModel.getTvShowsFavorite().observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTvShows)
    }
}