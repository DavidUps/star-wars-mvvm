package com.davidups.starwars.core.di

import com.davidups.skell.features.people.services.PeopleService
import com.davidups.starwars.features.authentication.services.AuthenticationService
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PeopleService(get()) }

    factory { AuthenticationService() }
}