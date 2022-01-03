package com.submission.moviesapp.ui.favorite.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.ui.movies.OnMoviesClickCallBack
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.list_items.view.*

class MoviesFavoriteAdapter : PagedListAdapter<Movies, MoviesFavoriteAdapter.MoviesFavoriteViewHolder>(DIFF_CALLBACK) {

    private var onMoviesClickCallBack: OnMoviesClickCallBack? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    fun setOnMoviesClickCallBack(onMoviesClickCallBack: OnMoviesClickCallBack) {
        this.onMoviesClickCallBack = onMoviesClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesFavoriteAdapter.MoviesFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MoviesFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesFavoriteAdapter.MoviesFavoriteViewHolder, position: Int) {
        val favMovies = getItem(position)
        if (favMovies != null) {
            holder.bind(favMovies)
        }
    }

    inner class MoviesFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: Movies) {
            with(itemView) {
                tv_name.text = movies.title
                tv_date.text = movies.releaseDate
                tv_rate.text = movies.rate.toString()
                Glide.with(context)
                    .load(Constant.IMAGE_URL + movies.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_broken_image)
                    )
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                    .into(poster)

                setOnClickListener {
                    onMoviesClickCallBack?.onItemClicked(movies)
                }
            }
        }
    }
}