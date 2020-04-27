package com.davidups.skell.core.di

import com.davidups.skell.features.people.services.PeopleService
import org.koin.dsl.module

val dataSourceModule = module {
    factory { PeopleService(get()) }
}