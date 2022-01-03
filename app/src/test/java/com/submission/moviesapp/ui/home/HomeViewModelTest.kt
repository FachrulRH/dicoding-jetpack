package com.submission.moviesapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.entities.sources.Repository
import com.submission.moviesapp.utils.DataDummy
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockitokotlin2.*
import com.submission.moviesapp.entities.TrendingTvShows
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private val page = 1

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovies: Observer<List<TrendingMovies>>

    @Mock
    private lateinit var observerTvShows: Observer<List<TrendingTvShows>>

    @Before
    fun setUp() {
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun generateMoviesTrending() {
        val dummyTrendingMovies = DataDummy.generateTrendingMovies()
        val trendingMovies = MutableLiveData<List<TrendingMovies>>()
        trendingMovies.value = dummyTrendingMovies

        `when`(repository.getMoviesTrending(page)).thenReturn(trendingMovies)
        val trending = viewModel.generateMoviesTrending(page).value
        verify(repository).getMoviesTrending(page)
        assertNotNull(trending)
        assertEquals(20, trending?.size)

        viewModel.generateMoviesTrending(page).observeForever(observerMovies)
        verify(observerMovies).onChanged(dummyTrendingMovies)
    }

    @Test
    fun generateTvShowsTrending() {
        val dummyTrendingTvShows = DataDummy.generateTrendingTvShows()
        val trendingTvShows = MutableLiveData<List<TrendingTvShows>>()
        trendingTvShows.value = dummyTrendingTvShows

        `when`(repository.getTvShowsTrending(page)).thenReturn(trendingTvShows)
        val trending = viewModel.generateTvShowsTrending(page).value
        verify(repository).getTvShowsTrending(page)
        assertNotNull(trending)
        assertEquals(20, trending?.size)

        viewModel.generateTvShowsTrending(page).observeForever(observerTvShows)
        verify(observerTvShows).onChanged(dummyTrendingTvShows)
    }
}