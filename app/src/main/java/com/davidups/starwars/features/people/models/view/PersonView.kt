package com.davidups.skell.features.people.models.view

import com.davidups.starwars.features.people.models.data.Person
import com.davidups.skell.core.extensions.empty
import java.io.Serializable

data class PersonView(
    var name: String?,
    var height: String?,
    var mass: String?,
    var hair_color: String?,
    var skin_color: String?,
    var eye_color: String?,
    var birth_year: String?,
    var gender: String?,
    var homeworld: String
) : Serializable {

    companion object {
        fun empty() =
            PersonView(
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