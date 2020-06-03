package com.davidups.starwars.features.movies.models.data

import com.davidups.starwars.features.movies.models.entity.MoviesEntity
import com.davidups.starwars.features.movies.models.view.MoviesView

data class Movies(
    val count: Int?,
    val next: String?,
    val previus: String?,
    val results: MutableList<Movie>?
) {

    fun toMoviesEntity() =
        MoviesEntity(count, next, previus, results?.map { it.toMovieEntity() }?.toMutableList())

    fun toMoviesView() =
        MoviesView(count, next, previus, results?.map { it.toMovieView() }?.toMutableList())
}