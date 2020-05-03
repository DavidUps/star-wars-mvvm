package com.davidups.starwars.features.people.services

import com.davidups.skell.features.people.models.entity.PeopleEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PeopleApi {

    companion object {
        private const val PEOPLE = "people"
        private const val PEOPLE_BY_PAGE = "$PEOPLE/{page}"
    }

    @GET(PEOPLE)
    suspend fun getPeople(): Response<PeopleEntity>

    @GET(PEOPLE)
    suspend fun getPeopleByPage(@Query("page") page: Int): Response<PeopleEntity>
}