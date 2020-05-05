package com.davidups.starwars.core.di

import com.davidups.starwars.features.people.usecases.GetPeople
import com.davidups.skell.features.people.usecases.GetPeopleByPage
import com.davidups.starwars.features.authentication.usescases.GetUser
import com.davidups.starwars.features.authentication.usescases.PutUser
import com.davidups.starwars.features.authentication.usescases.SignIn
import com.davidups.starwars.features.authentication.usescases.SignUp
import org.koin.dsl.module

val useCaseModule = module {

    factory { GetPeople(get()) }
    factory { GetPeopleByPage(get()) }

    factory { SignIn(get()) }
    factory { SignUp(get()) }
    factory { PutUser(get()) }
    factory { GetUser(get()) }
}