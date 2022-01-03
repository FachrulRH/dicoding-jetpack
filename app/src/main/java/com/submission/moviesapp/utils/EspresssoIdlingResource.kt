package com.submission.moviesapp.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspresssoIdlingResource {

    private const val RESOURCE = "GLOBAL"
    val espressoIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoIdlingResource.increment()
    }

    fun decrement() {
        espressoIdlingResource.decrement()
    }

}