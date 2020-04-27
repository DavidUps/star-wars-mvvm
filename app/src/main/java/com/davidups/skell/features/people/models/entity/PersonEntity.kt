package com.davidups.skell.features.people.models.entity

import com.davidups.skell.features.people.models.data.Person
import com.davidups.skell.core.extensions.empty

data class PersonEntity(
    var name: String?,
    var height: String?,
    var mass: String?,
    var hair_color: String?,
    var skin_color: String?,
    var eye_color: String?,
    var birth_year: String?,
    var gender: String?,
    var homeworld: String
) {

    companion object {
        fun empty() =
            PersonEntity(
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty(),
                String.empty()
            )
    }

    fun toPeople() =
        Person(
            name,
            height,
            mass,
            hair_color,
            skin_color,
            eye_color,
            birth_year,
            gender,
            homeworld
        )
}