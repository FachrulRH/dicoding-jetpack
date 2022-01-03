package com.submission.moviesapp.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockitokotlin2.*
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.Repository
import com.submission.moviesapp.utils.DataDummy
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel
    private val page = 1

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<TvShows>>

    @Before
    fun setUp() {
        viewModel = TvShowsViewModel(repository)
    }

    @Test
    fun generateTvShows() {
        val dummyTvShows = DataDummy.generateAlltvShows()
        val tvShows = MutableLiveData<List<TvShows>>()
        tvShows.value = dummyTvShows

        `when`(repository.getAllTvShows(page)).thenReturn(tvShows)
        val tvShow = viewModel.generateTvShows(page).value
        verify(repository).getAllTvShows(page)
        assertNotNull(tvShow)
        assertEquals(20, tvShow?.size)

        viewModel.generateTvShows(page).observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}