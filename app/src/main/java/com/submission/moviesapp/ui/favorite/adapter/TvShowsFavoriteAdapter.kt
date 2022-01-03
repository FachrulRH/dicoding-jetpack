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
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.ui.tvshows.OnTvShowsClickCallBack
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.list_items.view.*

class TvShowsFavoriteAdapter : PagedListAdapter<TvShows, TvShowsFavoriteAdapter.FavoriteTvShowsViewHolder>(DIFF_CALLBACK) {

    private var onTvShowsClickCallBack : OnTvShowsClickCallBack? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShows>() {
            override fun areItemsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShows, newItem: TvShows): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    fun setOnTvShowsClickCallBack(onTvShowsClickCallBack: OnTvShowsClickCallBack) {
        this.onTvShowsClickCallBack = onTvShowsClickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsFavoriteAdapter.FavoriteTvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return FavoriteTvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsFavoriteAdapter.FavoriteTvShowsViewHolder, position: Int) {
        val favTvShows = getItem(position)
        if (favTvShows != null) {
            holder.bind(favTvShows)
        }
    }

    inner class FavoriteTvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShows: TvShows) {
            with(itemView) {
                tv_name.text = tvShows.title
                tv_date.text = tvShows.releaseDate
                tv_rate.text = tvShows.rate.toString()
                Glide.with(context)
                    .load(Constant.IMAGE_URL + tvShows.poster)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading).error(R.drawable.ic_broken_image))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(15)))
                    .into(poster)

                setOnClickListener {
                    onTvShowsClickCallBack?.onItemClicked(tvShows)
                }
            }
        }
    }

}