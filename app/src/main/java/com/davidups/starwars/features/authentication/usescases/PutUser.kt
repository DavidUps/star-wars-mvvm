package com.davidups.starwars.features.authentication.usescases

import com.davidups.skell.core.interactor.UseCase
import com.davidups.starwars.core.functional.State
import com.davidups.starwars.features.authentication.models.entity.UserEntity
import kotlinx.coroutines.flow.Flow

class PutUser(private val authenticationRepository: AuthenticationRepository): UseCase<State<Boolean>, PutUser.Params>() {

    override fun run(params: Params?) = authenticationRepository.putUser(params!!.user)

    class Params(var user: UserEntity)
}