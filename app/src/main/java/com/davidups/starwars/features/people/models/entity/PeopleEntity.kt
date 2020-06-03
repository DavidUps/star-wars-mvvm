package com.davidups.skell.features.people.models.entity

import com.davidups.starwars.features.people.models.data.People

data class PeopleEntity(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: MutableList<PersonEntity>?
) {

    fun toPeople() = People(
        count,
        next,
        previous,
        results?.map { it.toPeople() }?.toMutableList()
    )
}