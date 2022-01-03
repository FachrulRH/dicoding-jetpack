package com.submission.moviesapp.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.submission.moviesapp.R
import com.submission.moviesapp.ui.favorite.adapter.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
 
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)

        supportActionBar?.elevation = 0f
    }
}