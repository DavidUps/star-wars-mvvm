package com.davidups.starwars.features.movies.services

import com.davidups.skell.features.people.models.entity.PeopleEntity
import com.davidups.starwars.features.movies.models.entity.MoviesEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MoviesApi {

    companion object {
        private const val FILMS = "films"
    }

    @GET(FILMS)
    suspend fun getMovies(): Response<MoviesEntity>

}