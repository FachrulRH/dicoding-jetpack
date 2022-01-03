package com.submission.moviesapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.TrendingTvShows
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.recent_items.view.*
import kotlin.collections.ArrayList

class TrendingTvShowsAdapter : RecyclerView.Adapter<TrendingTvShowsAdapter.TrendingTvShowsViewHolder>() {

    private val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    private val listTrending = ArrayList<TrendingTvShows>()

    fun setTrendingTvShows(recents: List<TrendingTvShows>) {
        listTrending.clear()
        listTrending.addAll(recents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingTvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recent_items, parent, false)
        return TrendingTvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrendingTvShowsViewHolder, position: Int) {
        val trending = listTrending[position]
        holder.bind(trending)
    }

    override fun getItemCount(): Int = listTrending.size

    inner class TrendingTvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(trending: TrendingTvShows) {
            with(itemView) {
                text_name.text = trending.name
                text_rate.text = trending.rate.toString()
                Glide.with(context)
                        .load(Constant.IMAGE_URL + trending.backgroundImages)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image))
                        .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                        .into(poster)
            }
        }
    }
}