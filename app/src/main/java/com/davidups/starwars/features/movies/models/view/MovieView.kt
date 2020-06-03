package com.davidups.starwars.features.movies.models.view

import com.davidups.skell.core.extensions.empty
import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.movies.models.data.Movie
import java.io.Serializable

data class MovieView(
    val title: String?,
    val episodeId: Int?,
    val openingCrawl: String?,
    val producer: String?,
    val releaseDate: String?
) : Serializable {

    companion object {
        fun empty() =
            MovieView(String.empty(), Int.empty(), String.empty(), String.empty(), String.empty())
    }

    fun toMovie() = Movie(title, episodeId, openingCrawl, producer, releaseDate)
}