package com.davidups.starwars.features.movies.models.entity

import com.davidups.skell.core.extensions.empty
import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.movies.models.data.Movie

data class MovieEntity(
    val title: String?,
    val episode_id: Int?,
    val opening_crawl: String?,
    val producer: String?,
    val release_date: String?
) {

    companion object {
        fun empty() =
            MovieEntity(String.empty(), Int.empty(), String.empty(), String.empty(), String.empty())
    }

    fun toMovie() = Movie(title, episode_id, opening_crawl, producer, release_date)
}

