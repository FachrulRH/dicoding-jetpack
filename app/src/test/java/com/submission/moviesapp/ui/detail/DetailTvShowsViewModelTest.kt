package com.submission.moviesapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.entities.sources.Repository
import com.submission.moviesapp.utils.DataDummy
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowsViewModelTest {


    private lateinit var viewModel: DetailTvShowsViewModel
    private var dummyTvShows = DataDummy.generateDetailtvShow()
    private var tvShowId = dummyTvShows.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var observerMovie: Observer<Movies>

    @Mock
    private lateinit var observerTvShow: Observer<TvShows>

    @Before
    fun setUp() {
        viewModel = DetailTvShowsViewModel(repository)
    }

    @Test
    fun generateTvShows() {
        val dummyTvShow = DataDummy.generateDetailtvShow()
        val detailTvShow = MutableLiveData<TvShows>()
        detailTvShow.value = dummyTvShow

        Mockito.`when`(repository.getTvShowById(tvShowId)).thenReturn(detailTvShow)
        val tvShow = viewModel.generateTvShows(tvShowId).value as TvShows
        verify(repository).getTvShowById(tvShowId)
        Assert.assertNotNull(tvShow)
        Assert.assertEquals(dummyTvShow.id, tvShow.id)
        Assert.assertEquals(dummyTvShow.description, tvShow.description)
        Assert.assertEquals(dummyTvShow.title, tvShow.title)
        Assert.assertEquals(dummyTvShow.poster, tvShow.poster)
        Assert.assertEquals(dummyTvShow.backgroundImages, tvShow.backgroundImages)
        Assert.assertEquals(dummyTvShow.releaseDate, tvShow.releaseDate)
        Assert.assertEquals(dummyTvShow.rate, tvShow.rate, 0.0)
        Assert.assertEquals(dummyTvShow.numberOfSeasons, tvShow.numberOfSeasons)
        Assert.assertEquals(dummyTvShow.tagline, tvShow.tagline)
        Assert.assertEquals(dummyTvShow.status, tvShow.status)

        viewModel.generateTvShows(tvShowId).observeForever(observerTvShow)
        verify(observerTvShow).onChanged(dummyTvShow)
    }

}