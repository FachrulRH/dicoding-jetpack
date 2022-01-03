package com.submission.moviesapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.Movies
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.list_items.view.*

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    private var listMovies = ArrayList<Movies>()
    private var onMoviesClickCallBack: OnMoviesClickCallBack? = null

    fun setOnMoviesClickCallBack(onMoviesClickCallBack: OnMoviesClickCallBack) {
        this.onMoviesClickCallBack = onMoviesClickCallBack
    }

    fun setListMovies(movies: List<Movies>?) {
        if (movies == null) return
        listMovies.clear()
        listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movies = listMovies[position]
        holder.bind(movies)
    }

    override fun getItemCount(): Int = listMovies.size

    inner class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movies: Movies) {
            with(itemView) {
                tv_name.text = movies.title
                tv_date.text = movies.releaseDate
                tv_rate.text = movies.rate.toString()
                Glide.with(context)
                    .load(Constant.IMAGE_URL + movies.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                    .into(poster)

                setOnClickListener {
                    onMoviesClickCallBack?.onItemClicked(movies)
                }
            }
        }
    }
}