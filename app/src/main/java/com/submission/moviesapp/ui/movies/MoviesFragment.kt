package com.submission.moviesapp.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.ui.detail.DetailMoviesActivity
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.progress_bar
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    private val moviesViewModel by viewModel<MoviesViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val moviesAdapter = MoviesAdapter()

            progress_bar.visibility = View.VISIBLE
            moviesViewModel.generateMovies(1).observe(viewLifecycleOwner, {
                progress_bar.visibility = View.GONE
                moviesAdapter.setListMovies(it)
            })

            with(rv_movies) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = moviesAdapter
            }

            moviesAdapter.setOnMoviesClickCallBack(object : OnMoviesClickCallBack{
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