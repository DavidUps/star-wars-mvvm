package com.davidups.skell.features.people.models.data

import com.davidups.skell.features.people.models.entity.PeopleEntity
import com.davidups.skell.features.people.models.view.PeopleView

data class People(
    var count: Int?,
    var next: String?,
    var previous: String?,
    var results: MutableList<Person>?
) {

    fun toPeopleView() =
        PeopleView(count, next, previous, results?.map { it.toPeopleView() }?.toMutableList())

    fun toPeopleEntity() =
        PeopleEntity(count, next, previous, results?.map { it.toPeopleEntity() }?.toMutableList())

}