package com.davidups.starwars.core.di

import com.davidups.starwars.features.authentication.usescases.AuthenticationRepository
import com.davidups.starwars.features.movies.usecases.MoviesRepository
import com.davidups.starwars.features.people.usecases.PeopleRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<AuthenticationRepository> { AuthenticationRepository.Network(get(), get()) }

    factory<MoviesRepository> { MoviesRepository.Network(get(), get()) }

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }
}