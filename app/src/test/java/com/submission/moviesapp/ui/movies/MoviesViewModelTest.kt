package com.submission.moviesapp.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockitokotlin2.*
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.sources.Repository
import com.submission.moviesapp.utils.DataDummy

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel
    private val page = 1

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observer: Observer<List<Movies>>

    @Before
    fun setUp() {
        viewModel = MoviesViewModel(repository)
    }

    @Test
    fun generateMovies() {
        val dummyMovies = DataDummy.generateAllMovies()
        val movies = MutableLiveData<List<Movies>>()
        movies.value = dummyMovies

        `when`(repository.getAllMovies(page)).thenReturn(movies)
        val movie = viewModel.generateMovies(page).value
        verify(repository).getAllMovies(page)
        assertNotNull(movie)
        assertEquals(20, movie?.size)

        viewModel.generateMovies(page).observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }
}