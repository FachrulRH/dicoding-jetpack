package com.submission.moviesapp.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.submission.moviesapp.R
import com.submission.moviesapp.ui.favorite.adapter.MoviesFavoriteAdapter
import com.submission.moviesapp.ui.favorite.adapter.TvShowsFavoriteAdapter
import com.submission.moviesapp.utils.DataDummy
import com.submission.moviesapp.utils.EspresssoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMoviesTrending = DataDummy.generateTrendingMovies()
    private val dummyTvShowsTrending = DataDummy.generateTrendingTvShows()
    private val dummyMovies = DataDummy.generateAllMovies()
    private val dummyTvShows = DataDummy.generateAlltvShows()
    private val dummyDetailMovies = DataDummy.generateDetailMovie()
    private val dummyDetailTvShows = DataDummy.generateDetailtvShow()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspresssoIdlingResource.espressoIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspresssoIdlingResource.espressoIdlingResource)
    }


    @Test
    fun loadMoviesTrending() {
        onView(withId(R.id.rv_trending_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMoviesTrending.size))
    }

    @Test
    fun loadTvShowsTrending() {
        onView(withId(R.id.rv_trending_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_trending_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowsTrending.size))
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.navigation_movies)).perform(click())
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.movies_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_rate)).check(matches(withText(dummyDetailMovies.rate.toString())))
        onView(withId(R.id.movies_release)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_release)).check(matches(withText(dummyDetailMovies.releaseDate)))
        onView(withId(R.id.movies_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_tagline)).check(matches(withText(dummyDetailMovies.tagline)))
        onView(withId(R.id.movies_status)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_status)).check(matches(withText(dummyDetailMovies.status)))
        onView(withId(R.id.poster)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.movies_desc)).check(matches(withText(dummyDetailMovies.description)))
    }

    @Test
    fun loadTvShows() {
        onView(withId(R.id.navigation_tvshows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.navigation_tvshows)).perform(click())
        onView(withId(R.id.rv_tv_shows)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tvshows_rate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_rate)).check(matches(withText(dummyDetailTvShows.rate.toString())))
        onView(withId(R.id.tvshows_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_release)).check(matches(withText(dummyDetailTvShows.releaseDate)))
        onView(withId(R.id.tvshows_tagline)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_tagline)).check(matches(withText(dummyDetailTvShows.tagline)))
        onView(withId(R.id.tvshows_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_status)).check(matches(withText(dummyDetailTvShows.status)))
        onView(withId(R.id.tvshows_season)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_season)).check(matches(withText(dummyDetailTvShows.numberOfSeasons.toString())))
        onView(withId(R.id.img_tvshows)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvshows_desc)).check(matches(withText(dummyDetailTvShows.description)))
    }

    @Test
    fun loadMoviesFavorite() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.rv_movies_fav)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShowsFavorite() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withText("Tv Show Favorite")).perform(click())
        onView(withId(R.id.rv_tv_fav)).check(matches(isDisplayed()))
    }
}

