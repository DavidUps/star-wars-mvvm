package com.davidups.skell.features.people.usecases

import com.davidups.starwars.core.functional.State
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.features.people.models.view.PeopleView
import com.davidups.starwars.features.people.usecases.PeopleRepository

class GetPeopleByPage(private val peopleRepository: PeopleRepository) :
    UseCase<State<PeopleView>, GetPeopleByPage.Params>() {

    override fun run(params: Params?) = peopleRepository.peopleByPage(params!!.page)

    class Params(var page: Int)
}