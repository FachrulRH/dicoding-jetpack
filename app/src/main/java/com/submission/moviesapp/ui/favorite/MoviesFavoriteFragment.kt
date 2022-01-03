package com.submission.moviesapp.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.ui.detail.DetailMoviesActivity
import com.submission.moviesapp.ui.favorite.adapter.MoviesFavoriteAdapter
import com.submission.moviesapp.ui.movies.OnMoviesClickCallBack
import kotlinx.android.synthetic.main.fragment_movies_favorite.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFavoriteFragment : Fragment() {

    private val moviesFavoriteViewModel by viewModel<FavoriteViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val moviesFavoriteAdapter = MoviesFavoriteAdapter()

            progress_bar.visibility = View.VISIBLE
            moviesFavoriteViewModel.getMoviesFavorite().observe(viewLifecycleOwner, {
                progress_bar.visibility = View.GONE
                moviesFavoriteAdapter.submitList(it)
            })

            with(rv_movies_fav) {
                adapter = moviesFavoriteAdapter
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
            }

            moviesFavoriteAdapter.setOnMoviesClickCallBack(object : OnMoviesClickCallBack {
                override fun onItemClicked(data: Movies) {
                    val intentMovies = Intent(context, DetailMoviesActivity::class.java).apply {
                        putExtra(DetailMoviesActivity.EXTRA_MOVIES, data.id)
                    }
                    startActivity(intentMovies)
                }
            })
        }
    }

}