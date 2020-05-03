package com.davidups.starwars.core.di

import com.davidups.starwars.features.authentication.usescases.AuthenticationRepository
import com.davidups.starwars.features.people.usecases.PeopleRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }

    factory<AuthenticationRepository> { AuthenticationRepository.Network(get(), get()) }

}