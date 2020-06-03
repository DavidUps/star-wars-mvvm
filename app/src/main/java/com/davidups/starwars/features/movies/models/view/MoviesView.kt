package com.davidups.starwars.features.movies.models.view

import com.davidups.skell.core.extensions.empty
import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.movies.models.data.Movies
import java.io.Serializable

data class MoviesView(
    val count: Int?,
    val next: String?,
    val previus: String?,
    val results: MutableList<MovieView>?
): Serializable {
    companion object {
        fun empty() = MoviesView(Int.empty(), String.empty(), String.empty(), mutableListOf())
    }

    fun toMovies() = Movies(count, next, previus, results?.map { it.toMovie() }?.toMutableList())
}