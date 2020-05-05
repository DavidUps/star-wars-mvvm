package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.functional.State
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.starwars.features.people.usecases.PeopleRepository

class GetPeople(private val peopleRepository: PeopleRepository): UseCase<State<PeopleView>, UseCase.None>() {
    override fun run(params: None?) = peopleRepository.people()
}