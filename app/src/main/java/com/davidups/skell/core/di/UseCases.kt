package com.davidups.skell.core.di

import com.davidups.skell.features.people.usecases.GetPeople
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPeople(get()) }
}