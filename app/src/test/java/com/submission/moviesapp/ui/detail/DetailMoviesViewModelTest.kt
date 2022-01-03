package com.submission.moviesapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Test
import com.nhaarman.mockitokotlin2.*
import com.submission.moviesapp.entities.Movies
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
class DetailMoviesViewModelTest {

    private lateinit var viewModel: DetailMoviesViewModel
    private var dummyMovie = DataDummy.generateDetailMovie()
    private var movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovie: Observer<Movies>

    @Before
    fun setUp() {
        viewModel = DetailMoviesViewModel(repository)
    }

    @Test
    fun generateMovies() {
        val dummyMovie = DataDummy.generateDetailMovie()
        val detailMovie = MutableLiveData<Movies>()
        detailMovie.value = dummyMovie

        `when`(repository.getMovieById(movieId)).thenReturn(detailMovie)
        val movie = viewModel.generateMovies(movieId).value as Movies
        verify(repository).getMovieById(movieId)
        assertNotNull(movie)
        assertEquals(dummyMovie.id, movie.id)
        assertEquals(dummyMovie.description, movie.description)
        assertEquals(dummyMovie.title, movie.title)
        assertEquals(dummyMovie.poster, movie.poster)
        assertEquals(dummyMovie.backgroundImages, movie.backgroundImages)
        assertEquals(dummyMovie.releaseDate, movie.releaseDate)
        assertEquals(dummyMovie.rate, movie.rate, 0.0)
        assertEquals(dummyMovie.tagline, movie.tagline)
        assertEquals(dummyMovie.status, movie.status)

        viewModel.generateMovies(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(dummyMovie)
    }
}