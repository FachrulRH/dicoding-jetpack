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
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.activity_detail_movies.*
import kotlinx.android.synthetic.main.activity_detail_tv_shows.*
import kotlinx.android.synthetic.main.activity_detail_tv_shows.fab
import kotlinx.android.synthetic.main.activity_detail_tv_shows.progress_bar
import kotlinx.android.synthetic.main.activity_detail_tv_shows.toolbar
import kotlinx.android.synthetic.main.activity_detail_tv_shows.toolbar_layout
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailTvShowsActivity : AppCompatActivity() {

    private val detailTvShowsViewModel by viewModel<DetailTvShowsViewModel>()
    private lateinit var tvShow: TvShows

    companion object {
        const val EXTRA_TV = "extra_tv"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tv_shows)

        setSupportActionBar(toolbar)

        toolbar_layout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white))
        toolbar_layout.setExpandedTitleTextAppearance(R.style.TextAppearance_MaterialComponents_Toolbar)

        val extras = intent.extras

        if (extras != null) {
            val tvShowsId = extras.getInt(EXTRA_TV)
            progress_bar.visibility = View.VISIBLE
            detailTvShowsViewModel.generateTvShows(tvShowsId).observe(this, { tvShows ->
                progress_bar.visibility = View.GONE
                tvShow = tvShows
                populateTvShows(tvShows)
                favoriteState()
            })
        }
        fab.setOnClickListener { onFloatingClicked() }
    }

    private fun populateTvShows(tvShow: TvShows) {
        toolbar_layout.title = tvShow.title
        tvshows_release.text = tvShow.releaseDate
        tvshows_tagline.text = tvShow.tagline
        tvshows_rate.text = tvShow.rate.toString()
        tvshows_status.text = tvShow.status
        tvshows_season.text = tvShow.numberOfSeasons.toString()
        tvshows_desc.text = tvShow.description

        Glide.with(this)
            .load(Constant.IMAGE_URL + tvShow.backgroundImages)
            .into(img_tvshows)
        Glide.with(this)
            .load(Constant.IMAGE_URL + tvShow.poster)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
            .into(tvshows_poster)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun onFloatingClicked() {
        if (detailTvShowsViewModel.isFavorite(tvShow)) {
            detailTvShowsViewModel.removeFavorite(tvShow)
            Snackbar.make(tvshows_nested_scroll, getString(R.string.tvshow_unfavorite), Snackbar.LENGTH_SHORT).show()
            fab.setImageDrawable(getDrawable(R.drawable.ic_unfavorite))
        } else {
            detailTvShowsViewModel.addFavorite(tvShow)
            Snackbar.make(tvshows_nested_scroll, getString(R.string.tvshow_favorite), Snackbar.LENGTH_SHORT).show()
            fab.setImageDrawable(getDrawable(R.drawable.ic_favorite))
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun favoriteState() {
        if (detailTvShowsViewModel.isFavorite(tvShow)) {
            fab.setImageDrawable(getDrawable(R.drawable.ic_favorite))
        } else {
            fab.setImageDrawable(getDrawable(R.drawable.ic_unfavorite))
        }
    }
}