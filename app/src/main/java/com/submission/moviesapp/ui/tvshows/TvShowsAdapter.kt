package com.submission.moviesapp.ui.tvshows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.submission.moviesapp.R
import com.submission.moviesapp.entities.TvShows
import com.submission.moviesapp.utils.Constant
import kotlinx.android.synthetic.main.list_items.view.*

class TvShowsAdapter : RecyclerView.Adapter<TvShowsAdapter.TvShowsViewHolder>() {

    private var listTvShows = ArrayList<TvShows>()
    private var onTvShowsClickCallBack: OnTvShowsClickCallBack? = null

    fun setOnTvShowsClickCallBack(onTvShowsClickCallBack: OnTvShowsClickCallBack) {
        this.onTvShowsClickCallBack = onTvShowsClickCallBack
    }

    fun setListTvShows(tvShows: List<TvShows>?) {
        if (tvShows == null) return
        listTvShows.clear()
        listTvShows.addAll(tvShows)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return TvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val tvShows = listTvShows[position]
        holder.bind(tvShows)
    }

    override fun getItemCount(): Int = listTvShows.size

    inner class TvShowsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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