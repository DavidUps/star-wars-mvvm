package com.davidups.skell.core.di

import com.davidups.skell.features.people.usecases.PeopleRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<PeopleRepository> { PeopleRepository.Network(get(), get()) }

}