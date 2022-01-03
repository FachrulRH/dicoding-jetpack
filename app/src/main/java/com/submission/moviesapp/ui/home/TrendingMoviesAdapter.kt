package com.submission.moviesapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.TrendingMovies
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.recent_items.view.*

class TrendingMoviesAdapter : RecyclerView.Adapter<TrendingMoviesAdapter.TrendingMoviesViewHolder>() {

    private val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    private var listTrending = ArrayList<TrendingMovies>()

    fun setTrendingMovies(recents: List<TrendingMovies>) {
        listTrending.clear()
        listTrending.addAll(recents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingMoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_items, parent, false)
        return TrendingMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingMoviesViewHolder, position: Int) {
        val trending = listTrending[position]
        holder.bind(trending)
    }

    override fun getItemCount(): Int = listTrending.size

    inner class TrendingMoviesViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(trendingMovies: TrendingMovies)  {
            with(itemView) {
                text_name.text = trendingMovies.title
                text_rate.text = trendingMovies.rate.toString()
                Glide.with(context)
                        .load(Constant.IMAGE_URL + trendingMovies.backgroundImages)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image))
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                        .into(poster)
            }
        }
    }
}