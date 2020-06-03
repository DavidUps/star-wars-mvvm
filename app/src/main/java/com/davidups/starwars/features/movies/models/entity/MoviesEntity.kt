package com.davidups.starwars.features.movies.models.entity

import com.davidups.skell.core.extensions.empty
import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.movies.models.data.Movies

data class MoviesEntity(
    val count: Int?,
    val next: String?,
    val previus: String?,
    val results: MutableList<MovieEntity>?
) {
    companion object {
        fun empty() = MoviesEntity(Int.empty(), String.empty(), String.empty(), mutableListOf())
    }

    fun toMovies() = Movies(count, next, previus, results?.map { it.toMovie() }?.toMutableList())
}