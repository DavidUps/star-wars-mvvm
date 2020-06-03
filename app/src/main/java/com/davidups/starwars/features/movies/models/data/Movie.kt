package com.davidups.starwars.features.movies.models.data

import com.davidups.starwars.features.movies.models.entity.MovieEntity
import com.davidups.starwars.features.movies.models.view.MovieView

data class Movie(
    val title: String?,
    val episodeId: Int?,
    val openingCrawl: String?,
    val producer: String?,
    val releaseDate: String?) {

    fun toMovieEntity() = MovieEntity(title,episodeId,openingCrawl, producer, releaseDate)

    fun toMovieView() = MovieView(title, episodeId, openingCrawl, producer, releaseDate)
}