package com.submission.moviesapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.ui.detail.DetailTvShowsActivity
import com.submission.moviesapp.ui.favorite.adapter.TvShowsFavoriteAdapter
import com.submission.moviesapp.ui.tvshows.OnTvShowsClickCallBack
import kotlinx.android.synthetic.main.fragment_tv_shows_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFavoriteFragment : Fragment() {

    private val tvShowsFavoriteViewModel by viewModel<FavoriteViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_shows_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvShowsFavoriteAdapter = TvShowsFavoriteAdapter()

            progress_bar.visibility = View.VISIBLE
            tvShowsFavoriteViewModel.getTvShowsFavorite().observe(viewLifecycleOwner, {
                progress_bar.visibility = View.GONE
                tvShowsFavoriteAdapter.submitList(it)
            })

            with(rv_tv_fav) {
                adapter = tvShowsFavoriteAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }

            tvShowsFavoriteAdapter.setOnTvShowsClickCallBack(object : OnTvShowsClickCallBack {
                override fun onItemClicked(data: TvShows) {
                    val intentMovies = Intent(context, DetailTvShowsActivity::class.java).apply {
                        putExtra(DetailTvShowsActivity.EXTRA_TV, data.id)
                    }
                    startActivity(intentMovies)
                }
            })
        }
    }

}