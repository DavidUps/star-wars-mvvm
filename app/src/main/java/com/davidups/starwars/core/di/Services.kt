package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.services.PeopleService
import com.davidups.starwars.features.authentication.services.AuthenticationService
import com.davidups.starwars.features.movies.services.MoviesService
import org.koin.dsl.module

val dataSourceModule = module {

    factory { AuthenticationService() }

    factory { MoviesService(get()) }

    factory { PeopleService(get()) }
}