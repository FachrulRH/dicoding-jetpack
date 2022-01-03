package com.submission.moviesapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.moviesapp.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val trendingMoviesAdapter = TrendingMoviesAdapter()
            val trendingTvShowsAdapter = TrendingTvShowsAdapter()

            setHasOptionsMenu(true)

            progress_bar.visibility = View.VISIBLE
            homeViewModel.generateMoviesTrending(1).observe(viewLifecycleOwner, { trendings ->
                if (trendings != null) {
                    trendingMoviesAdapter.setTrendingMovies(trendings)
                }
            })

            homeViewModel.generateTvShowsTrending(1).observe(viewLifecycleOwner, { trendings ->
                progress_bar.visibility = View.GONE
                if (trendings != null) {
                    trendingTvShowsAdapter.setTrendingTvShows(trendings)
                }
            })

            with(rv_trending_movies) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = trendingMoviesAdapter
            }

            with(rv_trending_tv) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = trendingTvShowsAdapter
            }
        }
    }
}