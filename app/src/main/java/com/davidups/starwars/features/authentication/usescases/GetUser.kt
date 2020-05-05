package com.davidups.starwars.features.authentication.usescases

import com.davidups.skell.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.authentication.models.view.UserView
import kotlinx.coroutines.flow.Flow

class GetUser(private val authenticationRepository: AuthenticationRepository): UseCase<State<UserView>, UseCase.None>() {
    override fun run(params: None?): Flow<State<UserView>> = authenticationRepository.getUser()
}