package com.davidups.skell.features.people.models.view

import com.davidups.skell.features.people.models.data.People
import com.davidups.skell.core.extensions.empty

data class PeopleView(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: MutableList<PersonView>?
) {

    companion object {
        fun empty() = PeopleView(0, String.empty(), String.empty(), mutableListOf())
    }

    fun toPeople() = People(
        count,
        next,
        previous,
        results?.map { it.toPeople() }?.toMutableList()
    )
}