package com.submission.moviesapp.ui.favorite.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.submission.moviesapp.R
import com.submission.moviesapp.ui.favorite.MoviesFavoriteFragment
import com.submission.moviesapp.ui.favorite.TvShowsFavoriteFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :  FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tab_text_1, R.string.tab_text_2)
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = MoviesFavoriteFragment()
            1 -> fragment = TvShowsFavoriteFragment()
        }
        return fragment as Fragment
    }
    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
    override fun getCount(): Int {
        return 2
    }

}