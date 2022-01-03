package com.submission.moviesapp.ui.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.activity_detail_movies.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {

    private val detailMoviesViewModel by viewModel<DetailMoviesViewModel>()
    private lateinit var movie: Movies

    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        setSupportActionBar(toolbar)

        toolbar_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar_layout.setExpandedTitleTextAppearance(R.style.TextAppearance_MaterialComponents_Toolbar)

        val extras = intent.extras

        if (extras != null) {
            val moviesId = extras.getInt(EXTRA_MOVIES)
            progress_bar.visibility = View.VISIBLE
            detailMoviesViewModel.generateMovies(moviesId).observe(this,  { movies ->
                progress_bar.visibility = View.GONE
                movie = movies
                populateMovies(movie)
                favoriteState()
            })
        }
        fab.setOnClickListener { onFloatingClicked() }
    }

    private fun populateMovies(movie: Movies) {
        toolbar_layout.title = movie.title
        movies_rate.text = movie.rate.toString()
        movies_tagline.text = movie.tagline
        movies_status.text = movie.status
        movies_release.text = movie.releaseDate
        movies_desc.text = movie.description
        Glide.with(this)
            .load(Constant.IMAGE_URL + movie.backgroundImages)
            .into(poster)
        Glide.with(this)
            .load(Constant.IMAGE_URL + movie.poster)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
            .into(movies_poster)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun onFloatingClicked() {
        if (detailMoviesViewModel.isFavorite(movie)) {
            detailMoviesViewModel.removeFavorite(movie)
            Snackbar.make(movies_nested_scroll, getString(R.string.movie_unfavorite), Snackbar.LENGTH_SHORT).show()
            fab.setImageDrawable(getDrawable(R.drawable.ic_unfavorite))
        } else {
            detailMoviesViewModel.addFavorite(movie)
            Snackbar.make(movies_nested_scroll, getString(R.string.movie_favorite), Snackbar.LENGTH_SHORT).show()
            fab.setImageDrawable(getDrawable(R.drawable.ic_favorite))
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun favoriteState() {
        if (detailMoviesViewModel.isFavorite(movie)) {
            fab.setImageDrawable(getDrawable(R.drawable.ic_favorite))
        } else {
            fab.setImageDrawable(getDrawable(R.drawable.ic_unfavorite))
        }
    }
}