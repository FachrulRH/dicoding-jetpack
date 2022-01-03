package com.submission.moviesapp.ui.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.ui.detail.DetailTvShowsActivity
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_tvshows.*
import kotlinx.android.synthetic.main.fragment_tvshows.progress_bar
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowsFragment : Fragment() {

    private val tvShowsViewModel by viewModel<TvShowsViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val tvShowsAdapter = TvShowsAdapter()

            progress_bar.visibility = View.VISIBLE
            tvShowsViewModel.generateTvShows(1).observe(viewLifecycleOwner, {
                progress_bar.visibility = View.GONE
                tvShowsAdapter.setListTvShows(it)
                tvShowsAdapter.notifyDataSetChanged()
            })


            with(rv_tv_shows) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowsAdapter
            }

            tvShowsAdapter.setOnTvShowsClickCallBack(object: OnTvShowsClickCallBack {
                override fun onItemClicked(data: TvShows) {
                    val intentTvShows = Intent(context, DetailTvShowsActivity::class.java).apply {
                        putExtra(DetailTvShowsActivity.EXTRA_TV, data.id)
                    }
                    startActivity(intentTvShows)
                }
            })
        }
    }
}