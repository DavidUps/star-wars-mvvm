package com.davidups.starwars.features.people.models.data

import com.davidups.skell.features.people.models.entity.PersonEntity
import com.davidups.skell.features.people.models.view.PersonView

data class Person(
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

    fun toPeopleView() =
        PersonView(
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

    fun toPeopleEntity() =
        PersonEntity(
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