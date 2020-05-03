package com.davidups.skell.features.people.services

import com.davidups.starwars.features.people.services.PeopleApi
import retrofit2.Retrofit

class PeopleService(retrofit: Retrofit) : PeopleApi {

    private val peopleApi by lazy { retrofit.create(PeopleApi::class.java) }

    override suspend fun getPeople() = peopleApi.getPeople()

    override suspend fun getPeopleByPage(page: Int) = peopleApi.getPeopleByPage(page)

}