package com.davidups.skell.features.people.usecases

import com.davidups.skell.core.functional.Result
import com.davidups.skell.core.interactor.UseCase
import com.davidups.skell.features.people.models.view.PeopleView

class GetPeople(private val peopleRepository: PeopleRepository): UseCase<Result<PeopleView>, UseCase.None>() {
    override fun run(params: None?) = peopleRepository.people()
}