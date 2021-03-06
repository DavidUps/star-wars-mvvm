package com.davidups.starwars.features.authentication.usescases

import com.davidups.starwars.core.functional.State
import com.davidups.skell.core.interactor.UseCase
import com.davidups.starwars.features.authentication.models.entity.UserEntity

class SignUp(private val authenticationRepository: AuthenticationRepository): UseCase<State<Boolean>, SignUp.Params>() {

    override fun run(params: Params?) = authenticationRepository.signUp(params!!.user)

    class Params(var user: UserEntity)

}