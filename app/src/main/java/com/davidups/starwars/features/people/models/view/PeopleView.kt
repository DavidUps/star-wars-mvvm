package com.davidups.starwars.features.people.models.view

import com.davidups.starwars.features.people.models.data.People
import com.davidups.skell.core.extensions.empty
import com.davidups.skell.features.people.models.view.PersonView
import java.io.Serializable

data class PeopleView(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: MutableList<PersonView>?
): Serializable {

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