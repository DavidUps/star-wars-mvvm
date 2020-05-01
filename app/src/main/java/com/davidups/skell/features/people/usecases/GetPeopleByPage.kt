package com.davidups.skell.features.people.usecases

import com.davidups.skell.core.functional.Result
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.features.people.models.view.PeopleView

class GetPeopleByPage(private val peopleRepository: PeopleRepository) :
    UseCase<Result<PeopleView>, GetPeopleByPage.Params>() {

    override fun run(params: Params?) = peopleRepository.peopleByPage(params!!.page)

    class Params(var page: Int)
}