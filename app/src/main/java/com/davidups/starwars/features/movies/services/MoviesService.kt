package com.davidups.starwars.features.movies.services

import retrofit2.Retrofit

class MoviesService(retrofit: Retrofit) : MoviesApi {

    private val movieApi by lazy { retrofit.create(MoviesApi::class.java) }

    override suspend fun getMovies() = movieApi.getMovies()
}